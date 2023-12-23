package com.google.minitube.repository;

import java.util.List;

import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;

public interface CommentRepository 
{
	public long save(Comment comment, Member member);
	public long saveChild(Comment comment, Member member);
	public long delete(int c_id);
	public long edit(int c_id, String c_content);
	public List<Comment> findAll(int v_id);
}
