package com.google.minitube.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.minitube.dto.Member;
import com.google.minitube.service.LikeService;

@Controller
@RequestMapping("/like")
public class LikeController 
{
	private final LikeService likeService;
	
	@Autowired
	public LikeController(LikeService likeService)
	{
		this.likeService = likeService;
	}
	
	@PostMapping("/video/{v_id}")
	public String Like(@PathVariable("v_id") int v_id, HttpServletRequest request)
	{
		System.out.println("[LikeController] Like");
		HttpSession session = request.getSession();
		Member memberVo = (Member)session.getAttribute("loginedMemberVo");
		likeService.save(v_id, memberVo.getM_id());
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
	
	@PostMapping("/delete/video/{v_id}")
	public String DisLike(@PathVariable("v_id") int v_id, HttpServletRequest request)
	{
		System.out.println("[DislikeController] Dislike");
		HttpSession session = request.getSession();
		Member memberVo = (Member)session.getAttribute("loginedMemberVo");
		
		likeService.delete(v_id, memberVo.getM_id());
		return "redirect:/video/watch/" + Integer.toString(v_id);
	}
}
