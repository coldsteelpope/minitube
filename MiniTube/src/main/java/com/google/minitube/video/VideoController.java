package com.google.minitube.video;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.minitube.comment.CommentVo;
import com.google.minitube.dto.Member;
import com.google.minitube.service.LikeService;
import com.google.minitube.video.util.UploadThumbnailService;
import com.google.minitube.video.util.UploadVideoService;

@Controller
@RequestMapping("/video")
public class VideoController 
{
	@Autowired
	VideoService videoService;
	
	@Autowired
	UploadThumbnailService uploadThumbnailService;
	
	@Autowired
	UploadVideoService uploadVideoService;
	
	@Autowired
	LikeService likeService;
	
	@GetMapping("/watch/{idx}")
	public String Watch(@PathVariable("idx") int idx, Model model, HttpServletRequest request)
	{
		VideoVo relatedVideo = videoService.GetRelatedVideo(idx);
		List<CommentVo> relatedComments = videoService.GetRelatedVideoComments(idx);
		
		if(relatedVideo != null)
		{
			model.addAttribute("video", relatedVideo);
			model.addAttribute("comments", relatedComments);
			
			HttpSession session = request.getSession();
			Member memberVo = (Member)session.getAttribute("loginedMemberVo");
			
			if(memberVo == null)
			{
				model.addAttribute("like", false);
				model.addAttribute("login", false);
			}
			else
			{
				boolean isLikeUser = likeService.isVideoLikedByUser(idx, memberVo.getM_id());
				model.addAttribute("like", isLikeUser);
				model.addAttribute("login", true);
			}
			return "video/watch";
		}
		else
		{
			return "video/watch";	
		}
	}
	
	@PostMapping("/delete/{idx}/{m_id}")
	public String Delete(@PathVariable("idx") int idx, @PathVariable("m_id") int m_id)
	{
		System.out.println("[VideoController] Delete(idx)");
		
		
		int result = videoService.deleteVideo(idx);
		
		return "redirect:/member/manage/" + Integer.toString(m_id);
	}
	
	@PostMapping("/edit/{idx}/{m_id}")
	public String Edit(@PathVariable("idx") int idx, @PathVariable("m_id") int m_id, VideoVo videoVo, @RequestParam("thumbnailFile") MultipartFile thumbnailFile)
	{
		System.out.println("[VideoController] Edit");
		int result = videoService.updateVideo(thumbnailFile, videoVo, idx);
		return "redirect:/member/manage/" + Integer.toString(m_id);
	}
	
	@PostMapping("/uploadVideoConfirm")
	public String UploadVideoConfirm(
			VideoVo videoVo, 
			@RequestParam("thumbnailFile") MultipartFile thumbnailFile, 
			@RequestParam("videoFile") MultipartFile videoFile, 
			HttpServletRequest request,
			RedirectAttributes redirectAttributes
	)
	{
		System.out.println("[VideoController] UploadVideoConfirm");
		
		String nextPage = null;

		HttpSession session = request.getSession();
		Member memberVo = (Member)session.getAttribute("loginedMemberVo");
		
		String savedThumbnailName = uploadThumbnailService.upload(thumbnailFile);
		String savedVideoName = uploadVideoService.upload(videoFile);
		
		if(savedThumbnailName != null && savedVideoName != null)
		{
			videoVo.setV_thumbnail(savedThumbnailName);
			videoVo.setV_video(savedVideoName);
			videoVo.setV_m_id(memberVo.getM_id());
			int result = videoService.uploadVideoConfirm(videoVo);
			
			if(result > 0)
			{
				nextPage = "member/profile/" + Integer.toString(videoVo.getV_m_id());
			}
			else
			{
				redirectAttributes.addFlashAttribute("fail", true);
				nextPage = "member/uploadVideo";
			}
		}
		else
		{
			redirectAttributes.addFlashAttribute("fail", true);
			nextPage = "member/uploadVideo";
		}
		
		return "redirect:/" + nextPage;
	}
	
	
}
