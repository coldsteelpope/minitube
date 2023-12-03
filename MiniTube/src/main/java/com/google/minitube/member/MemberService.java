package com.google.minitube.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService 
{
	@Autowired
	MemberDao memberDao;
	
	public MemberVo GetMember(int idx) 
	{
		System.out.println("[MemberService] GetMember");
		MemberVo member = memberDao.SelectMember(idx);
		return member;
	}

}
