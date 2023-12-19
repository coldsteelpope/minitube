package com.google.minitube.repository;

import com.google.minitube.dto.Member;

public interface MemberRepository 
{
	public long save(Member member);
	public Member find(Member member);
}
