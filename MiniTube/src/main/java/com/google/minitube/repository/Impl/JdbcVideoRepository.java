package com.google.minitube.repository.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.constants.VideoSql;
import com.google.minitube.dto.Video;
import com.google.minitube.repository.VideoRepository;

@Repository
public class JdbcVideoRepository implements VideoRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public long save(Video video) 
	{
		System.out.println("[JdbcVideoRepository] save");
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("v_title", video.getV_title());
		params.put("v_thumbnail", video.getV_thumbnail());
		params.put("v_description", video.getV_description());
		params.put("v_m_id", video.getV_m_id());
		params.put("v_category", video.getV_category());
		params.put("v_video", video.getV_video());
		
		return jdbcTemplate.update(VideoSql.INSERT_VIDEO, 
				params.get("v_title"),
				params.get("v_thumbnail"),
				params.get("v_description"),
				params.get("v_m_id"),
				params.get("v_category"),
				params.get("v_video")
		);
	}

	@Override
	public long update(MultipartFile thumbnailFile, Video video) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteById(int v_id) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Video findById(int v_id) {
		Map<String, ?> params = Collections.singletonMap("v_id", v_id);
		List<Video> videos = jdbcTemplate.query(VideoSql.SELECT_VIDEO_BY_ID, videoRowMapper(), params.get("v_id"));
		return (videos.size() == 0) ? null : videos.get(0);
	}

	@Override
	public List<Video> findAll() 
	{
		System.out.println("[JdbcVideoRepository] findAll");
		List<Video> videos = jdbcTemplate.query(VideoSql.SELECT_ALL_VIDEOS, videoRowMapper());
		return videos;
	}
	
	private RowMapper<Video> videoRowMapper()
	{
		return (rs, rosNum) -> {
			Video video = new Video();
			video.setV_m_id(rs.getInt("v_id"));
			video.setV_title(rs.getString("v_title"));
			video.setV_description(rs.getString("v_description"));
			video.setV_m_id(rs.getInt("v_m_id"));
			video.setV_category(rs.getString("v_category"));
			video.setV_video(rs.getString("v_video"));
			video.setV_thumbnail(rs.getString("v_thumbnail"));
			video.setV_mod_date(rs.getString("v_mod_date"));
			video.setV_reg_date(rs.getString("v_reg_date"));
			return video;
		};
	}
}
