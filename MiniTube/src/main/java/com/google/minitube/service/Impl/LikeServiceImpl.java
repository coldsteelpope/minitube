package com.google.minitube.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.minitube.dto.Like;
import com.google.minitube.repository.LikeRepository;
import com.google.minitube.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService 
{
	private final LikeRepository likeRepository;
	
	public LikeServiceImpl(LikeRepository likeRepository)
	{
		this.likeRepository = likeRepository;
	}
	
	@Override
	public List<Like> findAllVId(int v_id) 
	{
		System.out.println("[LikeServiceImpl] findAllVId");
		List<Like> likes = likeRepository.findAllByVId(v_id);
		return likes;
	}
	
	@Override
	public boolean isLike(int v_id, int m_id) 
	{
		System.out.println("[LikeServiceImpl] isLike");
		Like like = likeRepository.findByIds(v_id, m_id);
		return (like == null) ? false : true;
	}

	@Override
	public long save(int v_id, int m_id) 
	{
		System.out.println("[LikeServiceImpl] save");
		Like like = likeRepository.findByIds(v_id, m_id);
		if(like == null)
		{
			likeRepository.save(v_id, m_id);
			return 1;
		}
		else
		{
			System.out.println("[-] Already Like it!");
			return 0;
		}
	}

	@Override
	public long delete(int v_id, int m_id) 
	{
		System.out.println("[LikeServiceImpl] delete");
		Like like = likeRepository.findByIds(v_id, m_id);
		if(like == null)
		{
			System.out.println("[-] Like Yet");
			return 0;
		}
		else
		{
			likeRepository.delete(v_id, m_id);
			return 1;
		}
	}


}
