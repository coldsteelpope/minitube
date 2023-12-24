package com.google.minitube.service;

import java.util.List;

import com.google.minitube.dto.Like;

public interface LikeService 
{
	public List<Like> findAllVId(int v_id);
	public boolean isLike(int v_id, int m_id);
	public long save(int v_id, int m_id);
	public long delete(int v_id, int m_id);
}
