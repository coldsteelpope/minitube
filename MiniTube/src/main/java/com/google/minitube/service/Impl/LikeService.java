package com.google.minitube.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.minitube.dto.Like;
import com.google.minitube.repository.LikeDao;

@Service
public class LikeService 
{
	@Autowired
	LikeDao likeDao;
	
	public boolean isVideoLikedByUser(int v_id, int m_id)
	{
		System.out.println("[LikeService] isVideoLikedByUser");
		Like likeDto = likeDao.select(v_id, m_id);
		
		if(likeDto == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public void insert(int v_id, int m_id) 
	{
		System.out.println("[LikeService] insert");
		Like likeDto = likeDao.select(v_id, m_id);
		if(likeDto == null)
		{
			likeDao.insert(v_id, m_id);
		}
		else
		{
			System.out.println("좋아요를 이미 함");
		}
	}

	// 다음에 Refactoring할 때, dto를 넘기도록 고치기
	public void delete(int v_id, int m_id) 
	{
		System.out.println("[LikeService] delete");
		Like likeDto = likeDao.select(v_id, m_id);
		if(likeDto == null)
		{
			System.out.println("좋아요를 하지 않음");
		}
		else
		{
			likeDao.delete(v_id, m_id);
		}
	}
}
