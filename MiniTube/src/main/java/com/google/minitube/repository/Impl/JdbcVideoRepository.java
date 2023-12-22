package com.google.minitube.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Video;
import com.google.minitube.repository.VideoRepository;

@Repository
public class JdbcVideoRepository implements VideoRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public long save(Video video) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update(MultipartFile thumbnailFile, Video video) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteById(int v_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Video findById(int v_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
