package com.google.minitube.constants;

public class CommentSql 
{
	/* INSERT */
	public final static String INSERT_COMMENT = "INSERT INTO minitube_comment (c_m_id, c_v_id, c_content, c_reg_date, c_mod_date) VALUES (?, ?, ?, NOW(), NOW())";
	public final static String INSERT_CHILD_COMMENT = "INSERT INTO minitube_comment (c_v_id, c_m_id, c_c_id, c_content, c_reg_date, c_mod_date) VALUES (?, ?, ?, ?, NOW(), NOW())";
	
	/* SELECT */
	public final static String SELECT_ALL_COMMENTS = "SELECT * FROM minitube_comment WHERE c_v_id = ? AND c_c_id IS NULL";
	public final static String SELECT_CHILD_COMMENTS_BY_PARENT_ID = "SELECT * FROM minitube_comment WHERE c_c_id = ?";
	
	/* DELETE */
	public final static String DELETE_COMMENT = "DELETE FROM minitube_comment WHERE c_id = ?";
	public final static String DELETE_PARENT_COMMENTS_BY_C_V_ID = "DELETE FROM minitube_comment WHERE c_v_id = ? AND c_c_id IS NULL";
	public final static String DELETE_CHILD_COMMENTS_BY_C_V_ID = "DELETE FROM minitube_comment WHERE c_v_id = ? AND c_c_id IS NOT NULL"; 
	
	/* UPDATE */
	public final static String UPDATE_COMMENT = "UPDATE minitube_comment SET c_content = ? WHERE c_id = ?";
}
