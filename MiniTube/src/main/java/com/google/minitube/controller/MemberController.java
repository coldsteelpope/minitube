package com.google.minitube.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.constants.AuthConstants;
import com.google.minitube.dto.Member;
import com.google.minitube.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController 
{
	@Autowired
	MemberService memberSerivce;

	@GetMapping("/upload/video")
	public String uploadVideo(Model model)
	{
		if(model.getAttribute("fail") == null)
		{
			model.addAttribute("fail", false);
		}
		return "member/create/video";
	}
	
	@GetMapping("/manage")
	public String Manage(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Member loginedMember = (Member)session.getAttribute(AuthConstants.SessionName);
		if(loginedMember != null)
		{
			model.addAttribute("member", loginedMember);
			return "member/manage";
		}
		else
		{
			session.invalidate();
			return "redirect:/auth/signin";
		}
	}
	
	@GetMapping("/profile/{m_id}")
	public String Profile(@PathVariable("m_id") int m_id, Model model)
	{
		Member member = memberSerivce.findById(m_id);
		model.addAttribute("member", member);
		return "member/profile";
	}
	
	@PostMapping("/edit/{m_id}")
	public String Edit(@PathVariable("m_id") int m_id, HttpServletRequest request, @RequestParam("profileImageFile") MultipartFile profileImageFile)
	{
		System.out.println("[MemberController] Edit");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		long result = memberSerivce.update(member, profileImageFile);
		return "redirect:/member/manage";
	}
}
