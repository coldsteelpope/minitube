package com.google.minitube.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController 
{
	@Autowired
	MemberService memberService;
	
	@GetMapping(value = "/uploadVideo")
	public String uploadVideo()
	{
		return "member/create/video";
	}
	
	@GetMapping(value = "/uploadVideoSuccess")
	public String uploadVideoSuccess()
	{
		return "member/create/create_video_ok";
	}
	
	@GetMapping(value = "/uploadVideoFail")
	public String uploadVideoFial()
	{
		return "member/create/create_video_ng";
	}
	
	@GetMapping("/manage/{idx}")
	public String Manage(@PathVariable("idx") int idx, Model model, HttpServletRequest request)
	{
		String nextPage = "member/manage";
		MemberVo memberVo = memberService.GetMember(idx);
		
		HttpSession session = request.getSession();
		MemberVo loginedMemberVo = (MemberVo)session.getAttribute("loginedMemberVo");
		
		if(loginedMemberVo != null && idx == loginedMemberVo.getM_id())
		{
			model.addAttribute("member", memberVo);
			return nextPage;
		}
		else
		{
			// 해당하는 유저가 아닐때
			return "redirect:/auth/signin";
		}
	}
	
	@GetMapping("/profile/{idx}")
	public String Profile(@PathVariable("idx") int idx, Model model)
	{
		String nextPage = "member/profile";
		MemberVo memberVo = memberService.GetMember(idx);
		
		model.addAttribute("member", memberVo);
		String member_img = memberVo.getM_profile_img();
		member_img = (member_img == null) ? "/resources/blank_pic.png" : "/librarythumbs/profile/" + member_img;
		model.addAttribute("member_img", member_img);

		return nextPage;
	}
}
