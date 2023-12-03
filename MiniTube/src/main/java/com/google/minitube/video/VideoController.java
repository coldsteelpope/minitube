package com.google.minitube.video;

import java.util.ArrayList;
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

import com.google.minitube.comment.CommentVo;
import com.google.minitube.member.MemberVo;
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
	
	@GetMapping("/watch/{idx}")
	public String Watch(@PathVariable("idx") int idx, Model model)
	{
		VideoVo relatedVideo = videoService.GetRelatedVideo(idx);
		List<CommentVo> relatedComments = videoService.GetRelatedVideoComments(idx);
		
		if(relatedVideo != null)
		{
			model.addAttribute("video", relatedVideo);
			model.addAttribute("comments", relatedComments);
			return "video/watch";
		}
		else
		{
			return "video/watch";	
		}
	}
	
	@PostMapping("/uploadVideoConfirm")
	public String UploadVideoConfirm(VideoVo videoVo, @RequestParam("thumbnailFile") MultipartFile thumbnailFile, @RequestParam("videoFile") MultipartFile videoFile, HttpServletRequest request)
	{
		System.out.println("[VideoController] UploadVideoConfirm");
		
		String nextPage = "member/create/create_video_ok";
		
		//CreateDir();
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("loginedMemberVo");
		
		String savedThumbnailName = uploadThumbnailService.upload(thumbnailFile);
		String savedVideoName = uploadVideoService.upload(videoFile);
		
		if(savedThumbnailName != null && savedVideoName != null)
		{
			videoVo.setV_thumbnail(savedThumbnailName);
			videoVo.setV_video(savedVideoName);
			videoVo.setV_m_id(memberVo.getM_id());
			int result = videoService.uploadVideoConfirm(videoVo);
			if(result <= 0)
			{
				nextPage = "member/create/create_video_ng";
			}
		}
		else
		{
			nextPage = "member/create/create_video_ng";
		}
		
		return nextPage;
	}
	
	/*
	 * public void CreateDir() { String thumbUploadDir =
	 * "C:\\library\\upload\\thumbs"; String videoUploadDir =
	 * "C:\\library\\upload\\videos";
	 * 
	 * File f = new File(thumbUploadDir); if(f.exists() == false) { if(f.mkdir()) {
	 * System.out.println("thumbnail folder created!"); } else {
	 * System.out.println("thumbnail folder created fail!"); } }
	 * 
	 * f = new File(videoUploadDir); if(f.exists() == false) { if(f.mkdir()) {
	 * System.out.println("video folder created!"); } else {
	 * System.out.println("video folder created Fail!"); } } }
	 */
}
