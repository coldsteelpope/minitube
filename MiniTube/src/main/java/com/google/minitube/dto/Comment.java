package com.google.minitube.dto;

import java.util.List;

public class Comment 
{
	int c_id;
	int c_v_id;
	int c_m_id;
	int c_c_id;
	
	String c_content;
	String c_reg_date;
	String c_mod_date;
	
	Member c_member;
	List<Comment> comments;
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getC_v_id() {
		return c_v_id;
	}
	public void setC_v_id(int c_v_id) {
		this.c_v_id = c_v_id;
	}
	public int getC_m_id() {
		return c_m_id;
	}
	public void setC_m_id(int c_m_id) {
		this.c_m_id = c_m_id;
	}
	public int getC_c_id() {
		return c_c_id;
	}
	public void setC_c_id(int c_c_id) {
		this.c_c_id = c_c_id;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public String getC_reg_date() {
		return c_reg_date;
	}
	public void setC_reg_date(String c_reg_date) {
		this.c_reg_date = c_reg_date;
	}
	public String getC_mod_date() {
		return c_mod_date;
	}
	public void setC_mod_date(String c_mod_date) {
		this.c_mod_date = c_mod_date;
	}
	public Member getC_member() {
		return c_member;
	}
	public void setC_member(Member c_member) {
		this.c_member = c_member;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
