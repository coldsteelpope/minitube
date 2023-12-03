package com.google.minitube.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public MemberVo SelectMember(int m_id)
	{
		System.out.println("[MemberDao] SelectMember(m_id)");
		String sql = "SELECT * FROM minitube_member WHERE m_id = ?";
		List<MemberVo> memberVos = new ArrayList<MemberVo>();
		
		try
		{
			memberVos = jdbcTemplate.query(sql, new RowMapper<MemberVo>() {
				@Override
				public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					MemberVo memberVo = new MemberVo();
					return memberVo;
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
