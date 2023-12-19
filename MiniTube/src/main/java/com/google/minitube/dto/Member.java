package com.google.minitube.dto;


public class Member 
{
	
	int m_id;
	
	String m_profile_img;
	String m_mail;
	String m_pw;
	String m_firstname;
	String m_lastname;
	
	public String getM_profile_img() {
		return m_profile_img;
	}
	public void setM_profile_img(String m_profile_img) {
		this.m_profile_img = m_profile_img;
	}
	public int getM_id() {
		return m_id;
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
