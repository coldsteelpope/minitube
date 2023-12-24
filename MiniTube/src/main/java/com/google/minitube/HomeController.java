package com.google.minitube;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.minitube.dto.Video;
import com.google.minitube.service.VideoService;



@Controller
public class HomeController 
{
	private VideoService videoService;
	
	@Autowired
	public HomeController(VideoService videoService)
	{
		this.videoService = videoService;
	}

	@GetMapping(value = "/")
	public String home(Locale locale, Model model) 
	{
		System.out.println("[HomeController] home");
		
		List<Video> videos = videoService.findAll();
		List<Video> topVideos = videoService.findTopThree();
		
		model.addAttribute("videos", videos);
		model.addAttribute("topVideos", topVideos);
		
		return "home";
	}
	
	@GetMapping("/search")
	public String Search(Video videoVo, Model model)
	{
		System.out.println("[HomeController] Search");
		List<Video> videoVos = videoService.search(videoVo.getV_title());
		model.addAttribute("videos", videoVos);
		return "/search";
	}
}
