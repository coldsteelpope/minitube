package com.google.minitube.video;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.comment.CommentDao;
import com.google.minitube.comment.CommentVo;
import com.google.minitube.video.util.UploadThumbnailService;

@Service
public class VideoService 
{
	final static public int VIDEO_CREATE_SUCCESS = 1;
	final static public int VIDEO_CREATE_FAIL = -1;
	
	final static public int VIDEO_DELETE_SUCCESS = 1;
	final static public int VIDEO_DELETE_FAIL = -1;
	
	final static public int VIDEO_UPDATE_SUCCESS = 1;
	final static public int VIDEO_UPDATE_FAIL = -1;
	
	@Autowired
	VideoDao videoDao;
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UploadThumbnailService uploadThumbnailService;
	
	public int uploadVideoConfirm(VideoVo videoVo) 
	{
		System.out.println("[VideoService] uploadVideoConfirm");
		int result = videoDao.insertVideo(videoVo);
		
		if(result > 0)
		{
			return VIDEO_CREATE_SUCCESS;
		}
		else
		{
			return VIDEO_CREATE_FAIL;
		}
	}

	public VideoVo GetRelatedVideo(int v_id)
	{
		System.out.println("[VideoDao] GetRelatedVideo");
		VideoVo relatedVideo = videoDao.SelectVideo(v_id);
		return relatedVideo;
	}

	public List<CommentVo> GetRelatedVideoComments(int v_id)
	{
		List<CommentVo> commentVos = commentDao.SelectAllComments(v_id);
		return commentVos;
	}
	
	public List<VideoVo> GetAllVideos()
	{
		List<VideoVo> videoVos = videoDao.SelectAllVideos();
		return videoVos;
	}
	
	public List<VideoVo> GetSearchedVideos(String SearchQuery)
	{
		SearchQuery = '%' + SearchQuery + '%';
		List<VideoVo> videoVos = videoDao.SelectSearchVideos(SearchQuery);
		return videoVos;
 	}

	public int deleteVideo(int idx) 
	{
		int result = videoDao.deleteVideo(idx);
		if(result > 0)
		{
			System.out.println("[VideoService] Video Delete Success!");
			return VIDEO_DELETE_SUCCESS;
		}
		else
		{
			System.out.println("[VideoService] Video Delete Fail!");
			return VIDEO_DELETE_FAIL;
		}
	}

	public List<VideoVo> GetTopThreeVideos() 
	{
		List<VideoVo> topVideoVos = videoDao.SelectTopThreeVideos();
		return topVideoVos;
	}
	
	public int updateVideo(MultipartFile thumbnailFile, VideoVo videoVo, int v_id)
	{
		System.out.println("[VideoService] updateVideo");
		String savedThumbnailName = null;
		
		if(thumbnailFile.isEmpty() == false)
		{
			savedThumbnailName = uploadThumbnailService.upload(thumbnailFile);	
		}

		int result = videoDao.updateVideo(savedThumbnailName, videoVo, v_id);
		
		if(result > 0)
		{
			System.out.println("[VideoService] Update Success!");
			return VIDEO_UPDATE_SUCCESS;
		}
		else
		{
			System.out.println("[VideoService] Update Fail!");
			return VIDEO_UPDATE_FAIL;
		}
	}
}
