package com.google.minitube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.minitube.repository.CommentRepository;
import com.google.minitube.repository.LikeRepository;
import com.google.minitube.repository.MemberRepository;
import com.google.minitube.repository.VideoRepository;
import com.google.minitube.repository.Impl.JdbcCommentRepository;
import com.google.minitube.repository.Impl.JdbcLikeRepository;
import com.google.minitube.repository.Impl.JdbcMemberRepository;
import com.google.minitube.repository.Impl.JdbcVideoRepository;
import com.google.minitube.util.ProfileImageUtil;
import com.google.minitube.util.ThumbnailImageUtil;
import com.google.minitube.util.VideoUtil;
import com.google.minitube.util.Impl.LocalProfileImageUtilImpl;
import com.google.minitube.util.Impl.LocalThumbnailImageUtilImpl;
import com.google.minitube.util.Impl.LocalVideoUtilImpl;

@Configuration
public class SpringConfig 
{
	@Bean
	public MemberRepository memberRepository()
	{
		return new JdbcMemberRepository();
	}
	
	@Bean
	public VideoRepository videoRepository()
	{
		return new JdbcVideoRepository();
	}
	
	@Bean
	public CommentRepository commentRepository()
	{
		return new JdbcCommentRepository(memberRepository());
	}
	
	@Bean
	public LikeRepository likeRepository()
	{
		return new JdbcLikeRepository();
	}
	
	@Bean
	public VideoUtil videoUtil()
	{
		return new LocalVideoUtilImpl();
	}
	
	@Bean
	public ProfileImageUtil profileImageUtil()
	{
		return new LocalProfileImageUtilImpl();
	}
	
	@Bean
	public ThumbnailImageUtil thumbnailImageUtil()
	{
		return new LocalThumbnailImageUtilImpl();
	}
}
