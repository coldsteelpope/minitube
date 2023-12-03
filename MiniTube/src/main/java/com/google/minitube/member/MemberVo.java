package com.google.minitube.member;

import java.util.List;

import com.google.minitube.video.VideoVo;

public class MemberVo 
{
	int m_id;
	
	String m_mail;
	String m_pw;
	String m_firstname;
	String m_lastname;
	
	List<VideoVo> m_videos;
	
	public int getM_id() {
		return m_id;
	}
	public List<VideoVo> getM_videos() {
		return m_videos;
	}
	public void setM_videos(List<VideoVo> m_videos) {
		this.m_videos = m_videos;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_mail() {
		return m_mail;
	}
	public void setM_mail(String m_mail) {
		this.m_mail = m_mail;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_firstname() {
		return m_firstname;
	}
	public void setM_firstname(String m_firstname) {
		this.m_firstname = m_firstname;
	}
	public String getM_lastname() {
		return m_lastname;
	}
	public void setM_lastname(String m_lastname) {
		this.m_lastname = m_lastname;
	}
	
}
