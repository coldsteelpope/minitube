package com.google.minitube.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.minitube.member.MemberVo;

@Controller
@RequestMapping("/auth")
public class AuthController 
{
	@Autowired
	AuthService authService;
	
	@GetMapping(value = "/signup")
	public String SignUp()
	{
		return "auth/signup";
	}
	
	@GetMapping(value = "/signin")
	public String SignIn()
	{
		return "auth/signin";
	}
	
	@PostMapping(value = "/signinConfirm")
	public String SigninConfirm(MemberVo memberVo, HttpSession session)
	{
		System.out.println("[MemberController] SigninConfirm(MemberVo memberVo)");
		
		String nextPage = "auth/signinsuccess";
		MemberVo loginedMemberVo = authService.SigninConfirm(memberVo);
		if(loginedMemberVo == null)
		{
			nextPage = "auth/signinfail";
		}
		else
		{
			session.setAttribute("loginedMemberVo", loginedMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}
		return nextPage;
	}
	
	@PostMapping(value = "/signupConfirm")
	public String SignupConfirm(MemberVo memberVo)
	{
		System.out.println("[MemberController] SignupConfirm(MemberVo memberVo)");
		
		String nextPage = "auth/createsuccess";
		int result = authService.SignupConfirm(memberVo);
		if(result <= 0)
		{
			nextPage = "auth/createfail";
		}
		return nextPage;
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
