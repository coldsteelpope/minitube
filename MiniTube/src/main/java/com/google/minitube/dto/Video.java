package com.google.minitube.dto;

public class Video 
{
	int v_id;
	int v_m_id;
	
	String v_title;
	String v_thumbnail;
	String v_description;

	String v_category;
	String v_video;
	String v_reg_date;
	String v_mod_date;
	
	public int getV_id() {
		return v_id;
	}
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	public int getV_m_id() {
		return v_m_id;
	}
	public void setV_m_id(int v_m_id) {
		this.v_m_id = v_m_id;
	}
	public String getV_title() {
		return v_title;
	}
	public void setV_title(String v_title) {
		this.v_title = v_title;
	}
	public String getV_thumbnail() {
		return v_thumbnail;
	}
	public void setV_thumbnail(String v_thumbnail) {
		this.v_thumbnail = v_thumbnail;
	}
	public String getV_description() {
		return v_description;
	}
	public void setV_description(String v_description) {
		this.v_description = v_description;
	}
	public String getV_category() {
		return v_category;
	}
	public void setV_category(String v_category) {
		this.v_category = v_category;
	}
	public String getV_video() {
		return v_video;
	}
	public void setV_video(String v_video) {
		this.v_video = v_video;
	}
	public String getV_reg_date() {
		return v_reg_date;
	}
	public void setV_reg_date(String v_reg_date) {
		this.v_reg_date = v_reg_date;
	}
	public String getV_mod_date() {
		return v_mod_date;
	}
	public void setV_mod_date(String v_mod_date) {
		this.v_mod_date = v_mod_date;
	}
}
