package com.google.minitube.service;

import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Member;

public interface MemberService 
{
	public long save(Member member);
	public long update(Member member, MultipartFile profileImgFile);
	public Member findByEmail(String m_mail);
	public Member findById(int m_id);
	public long login(String m_mail, String m_pw);
}
