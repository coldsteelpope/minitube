package com.google.minitube.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.minitube.member.MemberVo;

@Service
public class AuthService 
{
	final static public int ACCOUNT_ALREADY_EXIST = 0;
	final static public int ACCOUNT_CREATE_SUCCESS = 1;
	final static public int ACCOUNT_CREATE_FAIL = -1;
	
	@Autowired
	AuthDao authDao;
	
	public int SignupConfirm(MemberVo memberVo)
	{
		System.out.println("[AuthService] SignupConfirm(MemberVo memberVo)");
		
		boolean isMember = authDao.IsMember(memberVo.getM_mail());
		if(isMember == false)
		{
			int result = authDao.InsertAccount(memberVo);
			if(result > 0)
			{
				return ACCOUNT_CREATE_SUCCESS;
			}
			else
			{
				return ACCOUNT_CREATE_FAIL;
			}
		}
		else
		{
			return ACCOUNT_ALREADY_EXIST;
		}
	}

	public MemberVo SigninConfirm(MemberVo memberVo) {
		MemberVo loginedMemberVo = authDao.SelectMember(memberVo);
		
		if(loginedMemberVo != null)
		{
			System.out.println("[AuthService] MEMBER LOGIN SUCCESS");
		}
		else
		{
			System.out.println("[AuthService] MEMBER LOGIN FAIL");
		}
		return loginedMemberVo;
	}
}
