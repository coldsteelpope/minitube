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
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	VideoService videoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("[HomeController] home");
		
		List<VideoVo> videoVos = videoService.GetAllVideos();
		model.addAttribute("videos", videoVos);
		
		
		return "home";
	}
	
	@GetMapping("/search")
	public String Search(@RequestParam(value="s", required = false) String searchQuery, Model model)
	{
		System.out.println("[HomeController] Search");
		List<VideoVo> videoVos = videoService.GetSearchedVideos(searchQuery);
		model.addAttribute("videos", videoVos);
		return "/search";
	}
}
