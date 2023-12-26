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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.minitube.constants.AuthConstants;
import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;
import com.google.minitube.dto.Video;
import com.google.minitube.service.CommentService;
import com.google.minitube.service.MemberService;
import com.google.minitube.service.VideoService;

@Controller
@RequestMapping("/video")
public class VideoController 
{
	private final MemberService memberService;
	private final VideoService videoService;
	private final CommentService commentService;
	
	@Autowired
	public VideoController(VideoService videoService, CommentService commentService, MemberService memberService)
	{
		this.videoService = videoService;
		this.commentService = commentService;
		this.memberService = memberService;
	}
	
	
	@GetMapping("/sorry")
	public String Sorry()
	{
		return "video/sorry";
	}
	
	@GetMapping("/watch/{v_id}")
	public String Watch(@PathVariable("v_id") int v_id, Model model, HttpServletRequest request)
	{
		System.out.println("[VideoController] Watch");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);

		Video video = videoService.findById(v_id);
		if(video == null)
		{
			return "redirect:/video/sorry";
		}
		else
		{
			List<Comment> comments = commentService.findAllByVId(v_id);
			Member videoMember = memberService.findById(video.getV_m_id());
			
			model.addAttribute("video", video);
			model.addAttribute("comments", comments);
			model.addAttribute("member", member);
			model.addAttribute("videoMember", videoMember);

			return "video/watch";
		}
	}
	
	@PostMapping("/delete/{v_id}")
	public String Delete(@PathVariable("v_id") int v_id, HttpServletRequest request)
	{
		System.out.println("[VideoController] Delete");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		long result = videoService.delete(v_id);
		return "redirect:/member/manage/";
	}
	
	@PostMapping("/edit/{v_id}")
	public String Edit(
			@PathVariable("v_id") int v_id, 
			Video video, 
			@RequestParam("thumbnailFile") MultipartFile thumbnailFile, 
			HttpServletRequest request
	)
	{
		System.out.println("[VideoController] Edit");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		long result = videoService.update(v_id, video, thumbnailFile);
		
		return "redirect:/member/manage/";
	}
	
	@PostMapping("/create")
	public String Create(
		Video video,
		@RequestParam("thumbnailFile") MultipartFile thumbnailFile,
		@RequestParam("videoFile") MultipartFile videoFile,
		HttpServletRequest request,
		RedirectAttributes redirectAttributes
	)
	{
		System.out.println("[VideoController] Create");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		
		if(thumbnailFile == null || videoFile == null)
		{
			redirectAttributes.addFlashAttribute("fail", true);
			return "redirect:/member/upload/video";
		}
		else
		{
			long result = videoService.save(video, thumbnailFile, videoFile, member);
			if(result > 0)
			{
				return "redirect:/member/profile/" + Integer.toString(member.getM_id());
			}
			else
			{
				redirectAttributes.addFlashAttribute("fail", true);
				return "redirect:/member/upload/video";
			}
		}
	}
}
