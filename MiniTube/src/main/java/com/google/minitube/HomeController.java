package com.google.minitube;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.minitube.video.VideoService;
import com.google.minitube.video.VideoVo;


@Controller
public class HomeController 
{
	@Autowired
	VideoService videoService;

	@GetMapping(value = "/")
	public String home(Locale locale, Model model) {
		System.out.println("[HomeController] home");
		
		List<VideoVo> videoVos = videoService.GetAllVideos();
		
		List<VideoVo> topVideoVos = videoService.GetTopThreeVideos();
		
		model.addAttribute("videos", videoVos);
		model.addAttribute("topVideos", topVideoVos);
		
		return "home";
	}
	
	@GetMapping("/search")
	public String Search(VideoVo videoVo, Model model)
	{
		System.out.println("[HomeController] Search");
		List<VideoVo> videoVos = videoService.GetSearchedVideos(videoVo.getV_title());
		model.addAttribute("videos", videoVos);
		return "/search";
	}
}
