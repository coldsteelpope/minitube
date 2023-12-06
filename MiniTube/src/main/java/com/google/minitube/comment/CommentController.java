package com.google.minitube.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.minitube.member.MemberVo;

@Controller
@RequestMapping("/comment")
public class CommentController 
{
	@Autowired
	CommentService commentService;
	
	@PostMapping("/confirm/{v_id}")
	public String Confirm(CommentVo commentVo, @PathVariable("v_id") int v_id, HttpServletRequest request)
	{
		System.out.println("[CommentController] Confirm(commentVo, v_id)");
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("loginedMemberVo");
		int result = commentService.confirm(commentVo, memberVo, v_id);
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/childconfirm/{c_id}/{v_id}")
	public String ChildConfirm(CommentVo commentVo, @PathVariable("c_id") int c_id, @PathVariable("v_id") int v_id, HttpServletRequest request)
	{
		System.out.println("[CommentController] ChildConfirm(commentVo, c_id, request)");
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("loginedMemberVo");
		
		
		int result = commentService.ChildConfirm(commentVo, memberVo, v_id, c_id);
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/delete/{idx}/{v_id}")
	public String DeleteComment(@PathVariable("idx") int idx, @PathVariable("v_id") int v_id)
	{
		System.out.println("[CommentController] DeleteComment");
		
		int result = commentService.deleteComment(idx);
		
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/child/delete/{idx}/{v_id}")
	public String DeleteChildComment(@PathVariable("idx") int idx, @PathVariable("v_id") int v_id)
	{
		System.out.println("[CommentController] DeleteChildComment");
		int result = commentService.deleteChildComment(idx);
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
}
