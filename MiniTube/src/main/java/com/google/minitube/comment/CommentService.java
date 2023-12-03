package com.google.minitube.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.minitube.member.MemberVo;

@Service
public class CommentService 
{
	final static public int COMMENT_CREATE_SUCCESS = 1;
	final static public int COMMENT_CREATE_FAIL = -1;
	
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
	
}
