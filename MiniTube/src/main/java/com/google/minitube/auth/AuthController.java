package com.google.minitube.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.events.Event;

import com.google.minitube.member.MemberVo;

@Controller
@RequestMapping("/auth")
public class AuthController 
{
	@Autowired
	AuthService authService;
	
	@GetMapping(value = "/signup")
	public String SignUp(Model model)
	{
		if(model.getAttribute("fail") == null)
		{
			model.addAttribute("fail", false);
		}
		return "auth/signup";
	}
	
	@GetMapping(value = "/signin")
	public String SignIn(Model model)
	{
		if(model.getAttribute("fail") == null)
		{
			model.addAttribute("fail", false);
		}
		return "auth/signin";
	}
	
	@PostMapping(value = "/signinConfirm")
	public String SigninConfirm(MemberVo memberVo, HttpSession session, RedirectAttributes redirectAttributes)
	{
		System.out.println("[MemberController] SigninConfirm(MemberVo memberVo)");
		
		String nextPage = "/";
		MemberVo loginedMemberVo = authService.SigninConfirm(memberVo);
		if(loginedMemberVo == null)
		{
			redirectAttributes.addFlashAttribute("fail", true);
			nextPage = "auth/signin";
		}
		else
		{
			session.setAttribute("loginedMemberVo", loginedMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}
		return "redirect:/" + nextPage;
	}
	
	@PostMapping(value = "/signupConfirm")
	public String SignupConfirm(MemberVo memberVo, Model model, RedirectAttributes redirectAttributes)
	{
		System.out.println("[MemberController] SignupConfirm(MemberVo memberVo)");
		
		String nextPage = "auth/signin";
		int result = authService.SignupConfirm(memberVo);
		
		if(result <= 0)
		{
			redirectAttributes.addFlashAttribute("fail", true);
			nextPage = "auth/signup";
		}
		return "redirect:/" + nextPage;
	}
	
	@GetMapping(value = "/signoutConfirm")
	public String SignoutConfirm(HttpSession session)
	{
		System.out.println("[MemberController] SignoutConfirm");
		String nextPage = "redirect:/";
		session.invalidate();
		return nextPage;
	}
}
