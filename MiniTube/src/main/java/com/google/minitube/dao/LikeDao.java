package com.google.minitube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.minitube.dto.LikeDto;

@Component
public class LikeDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	public LikeDto select(int v_id, int m_id) 
	{
		System.out.println("[LikeDao] select");
		String sql = "SELECT * FROM minitube_like WHERE v_id = ? AND m_id = ?";
		List<LikeDto> likeDtos = new ArrayList<LikeDto>();
		
		try
		{
			likeDtos = jdbcTemplate.query(sql, new RowMapper<LikeDto>() {
				@Override
				public LikeDto mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					LikeDto likeDto = new LikeDto();
					likeDto.setL_id(rs.getInt("l_id"));
					likeDto.setV_id(rs.getInt("v_id"));
					likeDto.setM_id(rs.getInt("m_id"));
					return likeDto;
				}
			}, v_id, m_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return likeDtos.size() > 0 ? likeDtos.get(0) : null;
	}

	public void insert(int v_id, int m_id) 
	{
		System.out.println("[LikeDao] insert");
		String sql = "INSERT INTO minitube_like";
		sql += "(m_id, v_id) ";
		sql += "VALUES (?, ?)";
		
		System.out.println("v_id: " + v_id + ", m_id: " + m_id);
		
		try
		{
			int result = jdbcTemplate.update(sql, m_id, v_id);
			System.out.println("result: " + result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void delete(int v_id, int m_id) 
	{
		String sql = "DELETE FROM minitube_like WHERE v_id = ? AND m_id = ?";
		try
		{
			jdbcTemplate.update(sql, v_id, m_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}
