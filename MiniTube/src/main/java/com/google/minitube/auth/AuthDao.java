package com.google.minitube.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.minitube.member.MemberVo;

@Component
public class AuthDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean IsMember(String m_mail) {
		System.out.println("[AuthDao] IsMember(String m_mail)");
		String sql = "SELECT COUNT(*) FROM minitube_member WHERE m_mail = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, m_mail);
		if(result > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int InsertAccount(MemberVo memberVo) {
		System.out.println("[AuthDao] InsertAccount(MemberVo memberVo)");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "INSERT INTO minitube_member(";
		
		sql += "m_mail, ";
		args.add(memberVo.getM_mail());
		
		sql += "m_pw, ";
		args.add(passwordEncoder.encode(memberVo.getM_pw()));
		
		sql += "m_lastname, ";
		args.add(memberVo.getM_lastname());
		
		sql += "m_firstname, ";
		args.add(memberVo.getM_firstname());
		
		sql += "m_reg_date, m_mod_date)";
		
		sql += "VALUES(?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		try
		{
			result = jdbcTemplate.update(sql, args.toArray());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	public MemberVo SelectMember(MemberVo memberVo) {
		System.out.println("[AuthDao] SelectMember(MemberVo memberVo)");
		String sql = "SELECT * FROM minitube_member WHERE m_mail = ?";
		List<MemberVo> memberVos = new ArrayList<MemberVo>();
		
		try
		{
			memberVos = jdbcTemplate.query(sql, new RowMapper<MemberVo>() {
				@Override
				public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					MemberVo memberVo = new MemberVo();
					memberVo.setM_id(rs.getInt("m_id"));
					memberVo.setM_mail(rs.getString("m_mail"));
					memberVo.setM_pw(rs.getString("m_pw"));
					memberVo.setM_firstname(rs.getString("m_firstname"));
					memberVo.setM_lastname(rs.getString("m_lastname"));
					return memberVo;
				}
			}, memberVo.getM_mail());
			
			if(!passwordEncoder.matches(memberVo.getM_pw(), memberVos.get(0).getM_pw()))
			{
				// 비밀번호가 틀린 경우 전부 지워버리기
				memberVos.clear();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return memberVos.size() > 0 ? memberVos.get(0) : null;
	}
}
