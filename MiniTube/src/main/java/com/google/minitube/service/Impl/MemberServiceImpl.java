package com.google.minitube.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.dto.Member;
import com.google.minitube.repository.MemberRepository;
import com.google.minitube.service.MemberService;
import com.google.minitube.util.ProfileImageUtil;
import com.google.minitube.util.Impl.LocalProfileImageUtilImpl;

@Service
public class MemberServiceImpl implements MemberService
{
	private final MemberRepository memberRepository;
	private final ProfileImageUtil profileImageUtil;
	
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository, ProfileImageUtil profileImageUtil)
	{
		this.memberRepository = memberRepository;
		this.profileImageUtil = profileImageUtil;
	}
	
	@Override
	public long save(Member member) 
	{
		return memberRepository.save(member);
	}
	
	@Override
	public long update(Member member, MultipartFile profileImgFile) 
	{
		if(profileImgFile != null)
		{
			String savedProfileImageName = profileImageUtil.upload(profileImgFile);
			member.setM_profile_img(savedProfileImageName);
		}
		
		return memberRepository.update(member);
	}

	@Override
	public Member findByEmail(String m_mail) 
	{
		Member findMember = memberRepository.findByEmail(m_mail);
		return findMember;
	}

	@Override
	public Member findById(int m_id) 
	{
		Member findMember = memberRepository.findById(m_id);
		return findMember;
	}

	@Override
	public long login(String m_mail, String m_pw) 
	{
		System.out.println("[MemberService] login");
		Member findMember = memberRepository.findByEmailAndPw(m_mail, m_pw);
		return findMember == null ? 0 : 1;
	}
}
