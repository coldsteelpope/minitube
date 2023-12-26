package com.google.minitube.service;

import java.util.List;

import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;

public interface CommentService 
{
	public long save(Comment comment, Member member, int v_id);
	public long saveChild(Comment comment, Member member, int v_id);
	public long delete(int c_id);
	public long deleteChild(int c_id);
	public long edit(int c_id, String c_content);
	public List<Comment> findAllByVId(int v_id);
}
