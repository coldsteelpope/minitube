package com.google.minitube.constants;

public class MemberSql 
{
	/* SELECT */
	public static final String SELECT_MEMBER_COUNT_BY_MAIL = "SELECT * FROM minitube_member WHERE m_mail = ?";
	public static final String SELECT_MEMBER_BY_MAIL = "SELECT * FROM minitube_member WHERE m_mail = ?";
	public static final String SELECT_MEMBER_BY_ID = "SELECT * FROM minitube_member WHERE m_id = ?";
	public static final String SELECT_MEMBER_BY_MAIL_AND_PW = "SELECT * FROM minitube_member WHERE m_mail = ? AND m_pw = ?";
	
	/* INSERT */
	public static final String INSERT_MEMBER = "INSERT INTO minitube_member (m_mail, m_pw, m_lastname, m_firstname, m_reg_date, m_mod_date) VALUES (?, ?, ?, ?, NOW(), NOW())";
	
	/* UPDATE */
	public static final String UPDATE_MEMBER = "UPDATE minitube_member SET m_profile_img = ? WHERE m_id = ?";
}
