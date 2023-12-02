package com.google.minitube.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
