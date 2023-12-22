package com.google.minitube.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import com.google.minitube.repository.MemberRepository;
import com.google.minitube.repository.Impl.JdbcMemberRepository;

@Configurable
public class SpringConfig 
{
	@Bean
	public MemberRepository memberRepository()
	{
		return new JdbcMemberRepository();
	}
}
