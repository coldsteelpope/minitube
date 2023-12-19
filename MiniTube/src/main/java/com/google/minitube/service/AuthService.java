package com.google.minitube.service;

import com.google.minitube.dto.Member;

public interface AuthService 
{
	public Member save(Member member);
	public Member find(Member member);
}
