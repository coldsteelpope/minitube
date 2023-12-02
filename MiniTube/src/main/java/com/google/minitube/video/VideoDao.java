package com.google.minitube.video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insertVideo(VideoVo videoVo) 
	{
		System.out.println("[VideoDao] insertVideo()");
		
		String sql = "INSERT INTO minitube_video";
		sql += "(v_title, v_thumbnail, v_description, v_m_id, v_category, v_video, v_reg_date, v_mod_date) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		try
		{
			result = jdbcTemplate.update(sql,
					videoVo.getV_title(),
					videoVo.getV_thumbnail(),
					videoVo.getV_description(),
					videoVo.getV_m_id(),
					videoVo.getV_category(),
					videoVo.getV_video()
			);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public VideoVo SelectVideo(int v_id)
	{
		System.out.println("[VideoDao] SelectVideo(String v_id)");
		String sql = "SELECT * FROM minitube_video WHERE v_id = ?";
		List<VideoVo> videoVos = new ArrayList<VideoVo>();
		try
		{
			videoVos = jdbcTemplate.query(sql, new RowMapper<VideoVo>() {
				@Override
				public VideoVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					VideoVo videoVo = new VideoVo();
					videoVo.setV_id(rs.getInt("v_id"));
					videoVo.setV_m_id(rs.getInt("v_m_id"));
					videoVo.setV_category(rs.getString("v_category"));
					videoVo.setV_description(rs.getString("v_description"));
					videoVo.setV_video(rs.getString("v_video"));
					videoVo.setV_thumbnail(rs.getString("v_thumbnail"));
					videoVo.setV_title(rs.getString("v_title"));
					videoVo.setV_reg_date(rs.getString("v_reg_date"));
					videoVo.setV_mod_date(rs.getString("v_mod_date"));
					return videoVo;
				}
			}, v_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return videoVos.size() > 0 ? videoVos.get(0) : null;
	}
}
