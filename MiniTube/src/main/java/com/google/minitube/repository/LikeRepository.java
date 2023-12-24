package com.google.minitube.repository;

import java.util.List;

import com.google.minitube.dto.Like;

public interface LikeRepository 
{
	public Like findByIds(int v_id, int m_id);
	public long save(int v_id, int m_id);
	public long delete(int v_id, int m_id);
	public List<Like> findAllByVId(int v_id);
}
