package com.google.minitube.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.minitube.member.MemberVo;

@Service
public class CommentService 
{
	final static public int COMMENT_CREATE_SUCCESS = 1;
	final static public int COMMENT_CREATE_FAIL = -1;
	
	final static public int COMMENT_DELETE_SUCCESS = 1;
	final static public int COMMENT_DELETE_FAIL = -1;
	
	@Autowired
	CommentDao commentDao;
	
	public int confirm(CommentVo commentVo, MemberVo memberVo, int v_id)
	{
		System.out.println("[CommentService] Confirm");
		int result = commentDao.InsertComment(commentVo, memberVo, v_id);
		if(result > 0)
		{
			System.out.println("[+] Comment Create Success!");
			return COMMENT_CREATE_SUCCESS;
		}
		else
		{
			System.out.println("[-] Comment Create Fail!");
			return COMMENT_CREATE_FAIL;
		}
	}

	public int ChildConfirm(CommentVo commentVo, MemberVo memberVo, int v_id, int c_id) 
	{
		System.out.println("[CommentService] ChildConfirm");
		int result = commentDao.InsertChildComment(commentVo, memberVo, v_id, c_id);
		if(result > 0)
		{
			System.out.println("[+] Child Comment Create Success");
			return COMMENT_CREATE_SUCCESS;
		}
		else
		{
			System.out.println("[-] Child Comment Create Fail!");
			return COMMENT_CREATE_FAIL;
		}
	}
	
	public int deleteChildComment(int idx)
	{
		int result = commentDao.deleteChildComment(idx);
		if(result > 0)
		{
			System.out.println("[CommentService] Delete Child Comment Success!");
			return COMMENT_DELETE_SUCCESS;
		}
		else
		{
			System.out.println("[CommentService] Delete Child Comment Fail!");
			return COMMENT_DELETE_FAIL;
		}
	}

	public int deleteComment(int idx) 
	{
		int result = commentDao.deleteComment(idx);
		if(result > 0)
		{
			System.out.println("[CommentService] Delete Comment Success!");
			return COMMENT_DELETE_SUCCESS;
		}
		else
		{
			System.out.println("[CommentService] Delete Comment Fail!");
			return COMMENT_DELETE_FAIL;
		}
	}	
}
