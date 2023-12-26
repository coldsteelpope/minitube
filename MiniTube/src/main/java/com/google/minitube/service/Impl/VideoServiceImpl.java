package com.google.minitube.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Member;
import com.google.minitube.dto.Video;
import com.google.minitube.repository.CommentRepository;
import com.google.minitube.repository.VideoRepository;
import com.google.minitube.service.VideoService;
import com.google.minitube.util.ThumbnailImageUtil;
import com.google.minitube.util.VideoUtil;

@Service
public class VideoServiceImpl implements VideoService
{
	private final ThumbnailImageUtil thumbnailImageUtil;
	private final VideoUtil videoUtil;
	
	private final CommentRepository commentRepository;
	private final VideoRepository videoRepository;
	
	@Autowired
	public VideoServiceImpl(VideoRepository videoRepository, VideoUtil videoUtil, ThumbnailImageUtil thumbnailImageUtil, CommentRepository commentRepository)
	{
		this.videoRepository = videoRepository;
		this.videoUtil = videoUtil;
		this.thumbnailImageUtil = thumbnailImageUtil;
		this.commentRepository = commentRepository;
	}
	
	
	@Override
	public long save(Video video, MultipartFile thumbnailFile, MultipartFile videoFile, Member member)
	{
		video.setV_m_id(member.getM_id());
		
		if(thumbnailFile.isEmpty() == false)
		{
			String savedThumbnailImageName = thumbnailImageUtil.upload(thumbnailFile);
			video.setV_thumbnail(savedThumbnailImageName);
		}
		
		if(videoFile.isEmpty() == false)
		{
			String savedVideoName = videoUtil.upload(videoFile);
			video.setV_video(savedVideoName);
		}
		
		long result = videoRepository.save(video);
		return result;
	}

	@Override
	public long delete(int v_id) 
	{
		System.out.println("[VideoService] delete");
		long deleteCommentResult = commentRepository.deleteAllByCVId(v_id);
		
		System.out.println("deleteCommentResult: " + deleteCommentResult);
		
		long result = videoRepository.deleteById(v_id);
		return result;
	}

	@Override
	public long update(int v_id, Video video, MultipartFile thumbnailFile) 
	{
		if(thumbnailFile.isEmpty() == false)
		{
			String savedThumbnailName = thumbnailImageUtil.upload(thumbnailFile);
			video.setV_thumbnail(savedThumbnailName);
		}
		return videoRepository.update(video, v_id);
	}

	@Override
	public Video findById(int v_id) 
	{
		Video video = videoRepository.findById(v_id);
		return video;
	}

	@Override
	public List<Video> findAll() 
	{
		List<Video> videos = videoRepository.findAll();
		return videos;
	}

	@Override
	public List<Video> search(String query) 
	{
		return videoRepository.findVideosByTitle(query);
	}

	@Override
	public List<Video> findTopThree() 
	{
		return videoRepository.findTopThreeVideos();
	}

	@Override
	public List<Video> findAllVideosByMId(int v_m_id) 
	{
		List<Video> videos = videoRepository.findAllVideosByMId(v_m_id);
		return videos;
	}
	
}
