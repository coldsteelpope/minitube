package com.google.minitube.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;
import com.google.minitube.repository.CommentRepository;
import com.google.minitube.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{
	private final CommentRepository commentRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository)
	{
		this.commentRepository = commentRepository;
	}

	@Override
	public long save(Comment comment, Member member, int v_id) 
	{
		System.out.println("[CommentService] save");
		comment.setC_v_id(v_id);
		return commentRepository.save(comment, member);
	}

	@Override
	public long saveChild(Comment comment, Member member, int v_id)
	{
		System.out.println("[CommentService] saveChild");
		comment.setC_v_id(v_id);
		return commentRepository.saveChild(comment, member);
	}

	@Override
	public long delete(int c_id) 
	{
		System.out.println("[CommentService] delete");
		return commentRepository.delete(c_id);
	}

	@Override
	public long deleteChild(int c_id) 
	{
		System.out.println("[CommentService] deleteChild");
		return commentRepository.deleteChild(c_id);
	}

	@Override
	public long edit(int c_id, String c_content) 
	{
		System.out.println("[CommentService] edit");
		return commentRepository.edit(c_id, c_content);
	}

	@Override
	public List<Comment> findAllByVId(int v_id) 
	{
		System.out.println("[CommentService] findAll");
		List<Comment> comments = commentRepository.findAllByVId(v_id);
		return comments;
	}
	
}
