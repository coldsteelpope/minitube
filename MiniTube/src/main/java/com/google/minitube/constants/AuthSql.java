package com.google.minitube.constants;

public class AuthSql 
{
	public static final String SELECT_USER_COUNT_BY_MAIL = "SELECT COUNT(*) FROM minitube_member WHERE m_mail = :m_mail";
	public static final String SELECT_USER_BY_MAIL = "SELECT * FROM minitube_member WHERE m_mail = :m_mail";
	public static final String INSERT_USER = "INSERT INTO minitube_member (m_mail, m_pw, m_lastname, m_firstname, m_reg_date, m_mod_date) VALUES (:m_mail, :m_pw, :m_lastname, :m_firstname, NOW(), NOW())";
}
