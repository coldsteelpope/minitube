package com.google.minitube.repository.Impl;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.google.minitube.constants.MemberSql;
import com.google.minitube.dto.Member;
import com.google.minitube.repository.MemberRepository;

@Repository
public class JdbcMemberRepository implements MemberRepository
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Member findByEmailAndPw(String m_mail, String m_pw) 
	{
		System.out.println("[JdbcMemberRepository] findByEmailAndPw");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m_mail", m_mail);
		params.put("m_pw", m_pw);
		
		List<Member> members = jdbcTemplate.query(MemberSql.SELECT_MEMBER_BY_MAIL, memberRowMapper(), params.get("m_mail"));
		if(passwordEncoder.matches(m_pw, members.get(0).getM_pw()) == false)
		{
			members.clear();
		}
		
		return members.size() > 0 ? members.get(0) : null;
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
			// 1이 반환 why? jdbctemplate update는 변형된 행의 개수를 반환하는 함수이기 때문에
			return jdbcTemplate.update(MemberSql.INSERT_MEMBER, 
					params.get("m_mail"),
					params.get("m_pw"),
					params.get("m_lastname"),
					params.get("m_firstname")
			);	
		}
	}
	
	@Override
	public Member findByEmail(String m_mail) 
	{
		System.out.println("[JdbcMemberRepository] findByEmail");
		
		Map<String, ?> params = Collections.singletonMap("m_mail", m_mail);
		List<Member> members = jdbcTemplate.query(MemberSql.SELECT_MEMBER_BY_MAIL, memberRowMapper(), params.get("m_mail"));
		
		return (members.size() == 0) ? null : members.get(0);
	}
	
	@Override
	public Member findById(int m_id) {
		System.out.println("[JdbcMemberRepository] findById");
		
		Map<String, ?> params = Collections.singletonMap("m_id", m_id);
		List<Member> members = jdbcTemplate.query(MemberSql.SELECT_MEMBER_BY_ID, memberRowMapper(), params.get("m_id"));
		
		return (members.size() == 0) ? null : members.get(0);
	}
	
	@Override
	public long update(Member member) 
	{
		System.out.println("[JdbcMemberRepository] update");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("m_profile_img", member.getM_profile_img());
		params.put("m_id", member.getM_id());
		
		return jdbcTemplate.update(MemberSql.UPDATE_MEMBER, params.get("m_profile_img"), params.get("m_id"));
	}
	
	private int selectUserCount(String m_mail)
	{
		Map<String, Object> params = Collections.singletonMap("m_mail", m_mail);
		try
		{
			return jdbcTemplate.queryForObject(MemberSql.SELECT_MEMBER_COUNT_BY_MAIL, Integer.class, params.get("m_mail"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	private RowMapper<Member> memberRowMapper()
	{
		return (rs, rosNum) -> {
			
			Member member = new Member();
			member.setM_id(rs.getInt("m_id"));
			member.setM_mail(rs.getString("m_mail"));
			member.setM_pw(rs.getString("m_pw"));
			member.setM_firstname(rs.getString("m_firstname"));
			member.setM_lastname(rs.getString("m_lastname"));
			
			return member;
		};
	}


}
