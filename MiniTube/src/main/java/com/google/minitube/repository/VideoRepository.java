package com.google.minitube.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Video;

public interface VideoRepository 
{
	public long save(Video video);
	public long update(Video video, int v_id);
	public long deleteById(int v_id);
	public Video findById(int v_id);
	public List<Video> findAll();
	public List<Video> findVideosByTitle(String v_title);
	public List<Video> findTopThreeVideos();
	public List<Video> findAllVideosByMId(int v_m_id);
}
