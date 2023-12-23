package com.google.minitube.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;
import com.google.minitube.repository.CommentRepository;

public class JdbcCommentRepository implements CommentRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public long save(Comment comment, Member member) 
	{
		
		return 0;
	}

	@Override
	public long saveChild(Comment comment, Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long delete(int c_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long edit(int c_id, String c_content) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Comment> findAll(int v_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
