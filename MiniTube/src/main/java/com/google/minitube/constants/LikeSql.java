package com.google.minitube.constants;

public class LikeSql 
{
	/* SELECT */
	public final static String SELECT_LIKES_BY_IDS = "SELECT * FROM minitube_like WHERE v_id = ? AND m_id = ?";
	public final static String SELECT_LIKES_BY_V_ID = "SELECT * FROM minitube_like WHERE v_id = ?";
	/* INSERT */
	public final static String INSERT_LIKE = "INSERT INTO minitube_like (m_id, v_id) VALUES (?, ?)";
	/* DELETE */
	public final static String DELETE_LIKE_BY_IDS = "DELETE FROM minitube_like WHERE v_id = ? AND m_id = ?";
}
