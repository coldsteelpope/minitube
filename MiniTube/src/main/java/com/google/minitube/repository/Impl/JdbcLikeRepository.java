package com.google.minitube.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.minitube.constants.LikeSql;
import com.google.minitube.dto.Like;
import com.google.minitube.repository.LikeRepository;

@Repository
public class JdbcLikeRepository implements LikeRepository
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Like> findAllByVId(int v_id) 
	{
		System.out.println("[JdbcLikeRepository] findAllByVId");
		List<Like> likes = jdbcTemplate.query(LikeSql.SELECT_LIKES_BY_V_ID, likeRowMapper(), v_id);
		return likes;
	}
	
	@Override
	public Like findByIds(int v_id, int m_id) 
	{
		System.out.println("[JdbcLikeRepository] findByIds");
		List<Like> likes = jdbcTemplate.query(LikeSql.SELECT_LIKES_BY_IDS, likeRowMapper(), v_id, m_id);
		return likes.size() == 0 ? null : likes.get(0);
	}

	@Override
	public long save(int v_id, int m_id) 
	{
		System.out.println("[JdbcLikeRepository] save");
		int result = jdbcTemplate.update(LikeSql.INSERT_LIKE, m_id, v_id);
		return result;
	}

	@Override
	public long delete(int v_id, int m_id) 
	{
		System.out.println("[JdbcLikeRepository] delete");
		int result = jdbcTemplate.update(LikeSql.DELETE_LIKE_BY_IDS, v_id, m_id);
		return result;
	}

	private RowMapper<Like> likeRowMapper()
	{
		return (rs, rosNum) -> {
			Like like = new Like();
			like.setL_id(rs.getInt("l_id"));
			like.setV_id(rs.getInt("v_id"));
			like.setM_id(rs.getInt("m_id"));
			return like;
		};
	}


}
