package com.google.minitube.repository;

import com.google.minitube.dto.Member;

public interface MemberRepository 
{	
	public long save(Member member);
	public long update(Member member);
	public Member findByEmail(String m_mail);
	public Member findById(int m_id);
	public Member findByEmailAndPw(String m_mail, String m_pw);
}
