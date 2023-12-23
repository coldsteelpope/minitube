package com.google.minitube.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Video;

public interface VideoService 
{
	public long save(Video video, MultipartFile thumbnailFile, MultipartFile videoFile);
	public long delete(int v_id);
	public long update(int v_id, Video video, MultipartFile thumbnailFile);
	
	public Video findById(int v_id);
	
	public List<Video> findAll();
	public List<Video> search(String query);
	public List<Video> findTopThree();
	
}
