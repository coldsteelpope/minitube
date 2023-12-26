package com.google.minitube.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.minitube.constants.AuthConstants;
import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;
import com.google.minitube.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController 
{
	private final CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService)
	{
		this.commentService = commentService;
	}
	
	@PostMapping("/create/{v_id}")
	public String Create(Comment comment, @PathVariable("v_id") int v_id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
	{
		System.out.println("[CommentController] Create");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		System.out.println("member name: " + member.getM_firstname() + " " + member.getM_lastname());
		
		long result = commentService.save(comment, member, v_id);
		
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("createCommentFail", true);
			
		}
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/child/create/{c_id}/{v_id}")
	public String CreateChild(Comment comment, @PathVariable("c_id") int c_id, @PathVariable("v_id") int v_id, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		System.out.println("[CommentController] CreateChild");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		comment.setC_c_id(c_id);
		long result = commentService.saveChild(comment, member, v_id);
		
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("createChildCommentFail", true);
		}
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/delete/{c_id}/{v_id}")
	public String Delete(@PathVariable("c_id") int c_id, @PathVariable("v_id") int v_id, RedirectAttributes redirectAttributes)
	{
		System.out.println("[CommentController] Delete");
		long result = commentService.delete(c_id);
		
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("deleteCommentFail", true);
		}
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/child/delete/{c_id}/{v_id}")
	public String DeleteChild(@PathVariable("c_id") int c_id, @PathVariable("v_id") int v_id, RedirectAttributes redirectAttributes)
	{
		System.out.println("[CommentController] DeleteChild");
		long result = commentService.delete(c_id);
		
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("deleteChildCommentFail", true);
		}
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/edit/{c_id}/{v_id}")
	public String Edit(@PathVariable("c_id") int c_id, @PathVariable("v_id") int v_id, RedirectAttributes redirectAttributes, Comment comment)
	{
		System.out.println("[CommentController] Edit");
		long result = commentService.edit(c_id, comment.getC_content());
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("editCommentFail", true);
		}
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
}
