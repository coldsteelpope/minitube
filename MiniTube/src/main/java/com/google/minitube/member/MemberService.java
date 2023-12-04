package com.google.minitube.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService 
{
	final static public int MEMBER_UPDATE_PROFILE_IMAGE_SUCCESS = 1;
	final static public int MEMBER_UPDATE_PROFILE_IMAGE_FAIL = -1;
	
	@Autowired
	MemberDao memberDao;
	
	public MemberVo GetMember(int idx) 
	{
		System.out.println("[MemberService] GetMember");
		MemberVo member = memberDao.SelectMember(idx);
		return member;
	}
	
	public int UpdateMemberProfileImage(String profileImageName, MemberVo memberVo)
	{
		System.out.println("[MemberService] UpdateMemberProfileImage");
		int result = memberDao.UpdateMemberProfileImage(profileImageName, memberVo);
		if(result > 0)
		{
			return MEMBER_UPDATE_PROFILE_IMAGE_SUCCESS;
		}
		else
		{
			return MEMBER_UPDATE_PROFILE_IMAGE_FAIL;
		}
	}

}
