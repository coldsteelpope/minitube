package com.google.minitube.member;


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

import com.google.minitube.member.util.UploadProfileImgService;

@Controller
@RequestMapping("/member")
public class MemberController 
{
	@Autowired
	MemberService memberService;
	
	@Autowired
	UploadProfileImgService uploadProfileImgService;
	
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
			String member_img = memberVo.getM_profile_img();
			member_img = (member_img == null) ? "/resources/blank_pic.png" : "/libraryProfiles/" + member_img;
			
			model.addAttribute("member_img", member_img);
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
		member_img = (member_img == null) ? "/resources/blank_pic.png" : "/libraryProfiles/" + member_img;
		model.addAttribute("member_img", member_img);

		return nextPage;
	}
	
	@PostMapping("/edit/profile-image/{idx}")
	public String EditProfileImage(@PathVariable("idx") int idx, @RequestParam("profileImageFile") MultipartFile profileImageFile, HttpServletRequest request)
	{
		System.out.println("[MemberController] EditProfileImage");

		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("loginedMemberVo");
		String savedProfileImageName = uploadProfileImgService.upload(profileImageFile);
		if(savedProfileImageName != null)
		{
			int result = memberService.UpdateMemberProfileImage(savedProfileImageName, memberVo);
		}
		return "redirect:/member/manage/" + Integer.toString(idx);
	}
}
