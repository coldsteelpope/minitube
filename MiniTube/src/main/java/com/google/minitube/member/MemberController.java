package com.google.minitube.member;


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
	
	@GetMapping("/profile/{idx}")
	public String Profile(@PathVariable("idx") int idx, Model model)
	{
		String nextPage = "member/profile";
		MemberVo memberVo = memberService.GetMember(idx);
		
		
		if(memberVo != null)
		{
			model.addAttribute("member", memberVo);
		}
		else
		{

		}
		return nextPage;
	}
}
