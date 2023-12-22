package com.google.minitube.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Video;

public interface VideoRepository 
{
	public long save(Video video);
	public long update(MultipartFile thumbnailFile, Video video);
	public long deleteById(int v_id);
	public Video findById(int v_id);
	public List<Video> findAll();
}
