package com.google.minitube.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService 
{
	final static public int VIDEO_CREATE_SUCCESS = 1;
	final static public int VIDEO_CREATE_FAIL = -1;
	
	@Autowired
	VideoDao videoDao;
	
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

}
