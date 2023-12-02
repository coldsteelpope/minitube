package com.google.minitube.member;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController 
{
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
}
