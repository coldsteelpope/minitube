package com.google.minitube.controller;

import java.util.List;

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
import com.google.minitube.dto.Video;
import com.google.minitube.service.MemberService;
import com.google.minitube.service.VideoService;

@Controller
@RequestMapping("/member")
public class MemberController 
{
	private final MemberService memberSerivce;
	private final VideoService videoService;
	
	@Autowired
	public MemberController(MemberService memberService, VideoService videoService)
	{
		this.memberSerivce = memberService;
		this.videoService = videoService;
	}

	@GetMapping("/upload/video")
	public String uploadVideo(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		if(member != null)
		{
			model.addAttribute("member", member);
			if(model.getAttribute("fail") == null)
			{
				model.addAttribute("fail", false);
			}
			return "member/create/video";
		}
		else
		{
			session.invalidate();
			return "redirect:/auth/signin";
		}
	}
	
	@GetMapping("/manage")
	public String Manage(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		if(member != null)
		{
			model.addAttribute("member", member);
			model.addAttribute("videos", videoService.findAllVideosByMId(member.getM_id()));

			return "member/manage";
		}
		else
		{
			session.invalidate();
			return "redirect:/auth/signin";
		}
	}
	
	@GetMapping("/profile/{m_id}")
	public String Profile(@PathVariable("m_id") int m_id, Model model, HttpServletRequest request)
	{
		Member profileMember = memberSerivce.findById(m_id);
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		List<Video> videos = videoService.findAllVideosByMId(m_id);
		
		model.addAttribute("videos", videos);
		model.addAttribute("member", member);
		model.addAttribute("profileMember", profileMember);
		model.addAttribute("isMe", (member == null) ? false : (member.getM_id() == profileMember.getM_id()));
		
		
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
