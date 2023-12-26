package com.google.minitube.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.minitube.constants.AuthConstants;
import com.google.minitube.dto.Member;
import com.google.minitube.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController 
{

	private final MemberService memberService;
	
	@Autowired
	public AuthController(MemberService memberService)
	{
		this.memberService = memberService;
	}
	
	@GetMapping("/signup")
	public String Signup(Model model, HttpServletRequest request)
	{
		System.out.println("[AuthController] Signup");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		model.addAttribute("member", member);
		if(member == null)
		{
			if(model.getAttribute("fail") == null)
			{
				model.addAttribute("fail", false);
			}
			return "auth/signup";
		}
		else
		{
			return "auth/signin";
		}
	}
	
	@GetMapping("/signin")
	public String Signin(Model model, HttpServletRequest request)
	{
		System.out.println("[AuthController] Signin");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(AuthConstants.SessionName);
		model.addAttribute("member", member);
		if(member == null)
		{
			if(model.getAttribute("fail") == null)
			{
				model.addAttribute("fail", false);
			}
			return "auth/signin";
		}
		else
		{
			return "redirect:/";
		}
	}
	
	@GetMapping("/signoutConfirm")
	public String SignoutConfirm(HttpSession session, Model model)
	{
		System.out.println("[AuthController] SignoutConfirm");
		
		session.invalidate();
		
		return "redirect:/auth/signin";
	}
	
	
	@PostMapping("/signinConfirm")
	public String SigninConfirm(Member member, HttpSession session, RedirectAttributes redirectAttributes)
	{
		System.out.println("[AuthController] SigninConfirm");
		long result = memberService.login(member.getM_mail(), member.getM_pw());
		
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("fail", true);
			return "auth/signin";
		}
		else
		{
			Member loginedMember = memberService.findByEmail(member.getM_mail());
			session.setAttribute(AuthConstants.SessionName, loginedMember);
			session.setMaxInactiveInterval(60 * 30);
			return "redirect:/";
		}
	}
	
	@PostMapping("/signupConfirm")
	public String SignupConfirm(Member member, Model model, RedirectAttributes redirectAttributes)
	{
		System.out.println("[AuthController] SignupConfirm");
		long result = memberService.save(member);
		if(result == 0)
		{
			redirectAttributes.addFlashAttribute("fail", true);
			return "auth/signup";
		}
		else
		{
			return "redirect:/auth/signin";
		}
	}
}
