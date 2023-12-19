package com.google.minitube.repository.Impl;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.minitube.constants.AuthSql;
import com.google.minitube.dto.Member;
import com.google.minitube.repository.MemberRepository;

@Repository
public class JdbcMemberRepository implements MemberRepository
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public int selectUserCount(String m_mail)
	{
		Map<String, ?> params = Collections.singletonMap("m_mail", m_mail);
		return jdbcTemplate.queryForObject(AuthSql.SELECT_USER_COUNT_BY_MAIL, Integer.class, params);
	}
	
	@Override
	public long save(Member member) 
	{
		System.out.println("[JdbcMemberRepository] save");

		int userCount = selectUserCount(member.getM_mail());
		if(userCount > 0)
		{
			return 0;
		}
		else
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("m_mail", member.getM_mail());
			params.put("m_pw", passwordEncoder.encode(member.getM_pw()));
			params.put("m_lastname", member.getM_lastname());
			params.put("m_firstname", member.getM_firstname());
			
			return jdbcTemplate.update(AuthSql.INSERT_USER, params);
		}
	}

	@Override
	public Member find(Member member) 
	{
		System.out.println("[JdbcMemberRepository] find");
		return null;
	}
	
	
	
}
