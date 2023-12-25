package com.google.minitube.constants;

public class VideoSql 
{
	/* INSERT */
	public static final String INSERT_VIDEO = "INSERT INTO minitube_video (v_title, v_thumbnail, v_description, v_m_id, v_category, v_video, v_reg_date, v_mod_date) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";
	
	/* SELECT */
	public static final String SELECT_VIDEO_BY_ID = "SELECT * FROM minitube_video WHERE v_id = ?";
	public static final String SELECT_ALL_VIDEOS = "SELECT * FROM minitube_video";
	public static final String SELECT_VIDEOS_BY_TITLE = "SELECT * FROM minitube_video WHERE v_title LIKE ? ORDER BY v_id DESC";
	public static final String SELECT_TOP_THREE_VIDEOS = "SELECT * FROM minitube_video ORDER BY v_mod_date DESC LIMIT 3";
	public static final String SELECT_ALL_VIDEOS_BY_M_ID = "SELECT * FROM minitube_video WHERE v_m_id = ?";
	
	/* DELETE */
	public static final String DELETE_VIDEO_BY_ID = "DELETE FROM minitube_video WHERE v_id = ?";
	
	
	/* UPDATE */
	public static final String UPDATE_VIDEO = "UPDATE minitube_video SET v_title = ?, v_description = ?, v_thumbnail = ? WHERE v_id = ?";
	public static final String UPDATE_VIDEO_EXCEPT_THUMBNAIL = "UPDATE minitube_video SET v_title = ?, v_description = ? WHERE v_id = ?";
}
