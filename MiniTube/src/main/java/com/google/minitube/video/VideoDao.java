package com.google.minitube.video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.member.MemberVo;

@Component
public class VideoDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insertVideo(VideoVo videoVo) 
	{
		System.out.println("[VideoDao] insertVideo()");
		
		String sql = "INSERT INTO minitube_video";
		sql += "(v_title, v_thumbnail, v_description, v_m_id, v_category, v_video, v_reg_date, v_mod_date) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1; 
		try
		{
			result = jdbcTemplate.update(sql,
					videoVo.getV_title(),
					videoVo.getV_thumbnail(),
					videoVo.getV_description(),
					videoVo.getV_m_id(),
					videoVo.getV_category(),
					videoVo.getV_video()
			);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public VideoVo SelectVideo(int v_id)
	{
		System.out.println("[VideoDao] SelectVideo(String v_id)");
		String sql = "SELECT * FROM minitube_video WHERE v_id = ?";
		List<VideoVo> videoVos = new ArrayList<VideoVo>();
		try
		{
			videoVos = jdbcTemplate.query(sql, new RowMapper<VideoVo>() {
				@Override
				public VideoVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					VideoVo videoVo = new VideoVo();
					videoVo.setV_id(rs.getInt("v_id"));
					videoVo.setV_m_id(rs.getInt("v_m_id"));
					videoVo.setV_category(rs.getString("v_category"));
					videoVo.setV_description(rs.getString("v_description"));
					videoVo.setV_video(rs.getString("v_video"));
					videoVo.setV_thumbnail(rs.getString("v_thumbnail"));
					videoVo.setV_title(rs.getString("v_title"));
					videoVo.setV_reg_date(rs.getString("v_reg_date"));
					videoVo.setV_mod_date(rs.getString("v_mod_date"));
					
					String memberSql = "SELECT * FROM minitube_member WHERE m_id = ?";
					List<MemberVo> memberVos = new ArrayList<MemberVo>();
					
					try
					{
						memberVos = jdbcTemplate.query(memberSql, new RowMapper<MemberVo>() {
							@Override
							public MemberVo mapRow(ResultSet m_rs, int m_rowNum) throws SQLException
							{
								MemberVo memberVo = new MemberVo();
								memberVo.setM_id(m_rs.getInt("m_id"));
								memberVo.setM_firstname(m_rs.getString("m_firstname"));
								memberVo.setM_lastname(m_rs.getString("m_lastname"));
								memberVo.setM_mail(m_rs.getString("m_mail"));
								memberVo.setM_pw(m_rs.getString("m_pw"));
								memberVo.setM_profile_img(m_rs.getString("m_profile_img"));			
								return memberVo;
							}
						}, videoVo.getV_m_id());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					videoVo.setV_member(memberVos.get(0));
					return videoVo;
				}
			}, v_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return videoVos.size() > 0 ? videoVos.get(0) : null;
	}
	
	public List<VideoVo> SelectAllVideos()
	{
		System.out.println("[VideoVo] SelectAllVideos");
		String sql = "SELECT * FROM minitube_video";
		List<VideoVo> videoVos = new ArrayList<VideoVo>();
		
		try
		{
			videoVos = jdbcTemplate.query(sql, new RowMapper<VideoVo>() {
				@Override
				public VideoVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					VideoVo videoVo = new VideoVo();
					videoVo.setV_id(rs.getInt("v_id"));
					videoVo.setV_m_id(rs.getInt("v_m_id"));
					videoVo.setV_category(rs.getString("v_category"));
					videoVo.setV_description(rs.getString("v_description"));
					videoVo.setV_video(rs.getString("v_video"));
					videoVo.setV_thumbnail(rs.getString("v_thumbnail"));
					videoVo.setV_title(rs.getString("v_title"));
					videoVo.setV_reg_date(rs.getString("v_reg_date"));
					videoVo.setV_mod_date(rs.getString("v_mod_date"));
					
					String memberSql = "SELECT * FROM minitube_member WHERE m_id = ?";
					List<MemberVo> memberVos = new ArrayList<MemberVo>();
					
					try
					{
						memberVos = jdbcTemplate.query(memberSql, new RowMapper<MemberVo>() {
							@Override
							public MemberVo mapRow(ResultSet m_rs, int m_rowNum) throws SQLException
							{
								MemberVo memberVo = new MemberVo();
								memberVo.setM_id(m_rs.getInt("m_id"));
								memberVo.setM_firstname(m_rs.getString("m_firstname"));
								memberVo.setM_lastname(m_rs.getString("m_lastname"));
								memberVo.setM_mail(m_rs.getString("m_mail"));
								memberVo.setM_pw(m_rs.getString("m_pw"));
								memberVo.setM_profile_img(m_rs.getString("m_profile_img"));			
								return memberVo;
							}
						}, videoVo.getV_m_id());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					videoVo.setV_member(memberVos.get(0));
					return videoVo;
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return videoVos;
	}
	
	public List<VideoVo> SelectSearchVideos(String SearchQuery)
	{
		System.out.println("[VideoVo] SelectSearchVideos");
		String sql = "SELECT * FROM minitube_video WHERE v_title LIKE ? ORDER BY v_id DESC";
		List<VideoVo> videoVos = new ArrayList<VideoVo>();
		
		try
		{
			videoVos = jdbcTemplate.query(sql, new RowMapper<VideoVo>() {
				@Override
				public VideoVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					VideoVo videoVo = new VideoVo();
					videoVo.setV_id(rs.getInt("v_id"));
					videoVo.setV_m_id(rs.getInt("v_m_id"));
					videoVo.setV_category(rs.getString("v_category"));
					videoVo.setV_description(rs.getString("v_description"));
					videoVo.setV_video(rs.getString("v_video"));
					videoVo.setV_thumbnail(rs.getString("v_thumbnail"));
					videoVo.setV_title(rs.getString("v_title"));
					videoVo.setV_reg_date(rs.getString("v_reg_date"));
					videoVo.setV_mod_date(rs.getString("v_mod_date"));
					
					String memberSql = "SELECT * FROM minitube_member WHERE m_id = ?";
					List<MemberVo> memberVos = new ArrayList<MemberVo>();
					
					try
					{
						memberVos = jdbcTemplate.query(memberSql, new RowMapper<MemberVo>() {
							@Override
							public MemberVo mapRow(ResultSet m_rs, int m_rowNum) throws SQLException
							{
								MemberVo memberVo = new MemberVo();
								memberVo.setM_id(m_rs.getInt("m_id"));
								memberVo.setM_firstname(m_rs.getString("m_firstname"));
								memberVo.setM_lastname(m_rs.getString("m_lastname"));
								memberVo.setM_mail(m_rs.getString("m_mail"));
								memberVo.setM_pw(m_rs.getString("m_pw"));
								memberVo.setM_profile_img(m_rs.getString("m_profile_img"));			
								return memberVo;
							}
						}, videoVo.getV_m_id());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					videoVo.setV_member(memberVos.get(0));
					return videoVo;
				}
			}, SearchQuery);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return videoVos;
	}

	public int deleteVideo(int idx) {
		String sql = "DELETE FROM minitube_video WHERE v_id = ?";
		int result = jdbcTemplate.update(sql, idx);
		return result;
	}

	public List<VideoVo> SelectTopThreeVideos() 
	{
		System.out.println("[VideoDao] SelectTopThreeVideos");
		String sql = "SELECT * FROM minitube_video ORDER BY v_mod_date DESC LIMIT 3";
		List<VideoVo> videoVos = new ArrayList<VideoVo>();
		
		try
		{
			videoVos = jdbcTemplate.query(sql, new RowMapper<VideoVo>() {
				@Override
				public VideoVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					VideoVo videoVo = new VideoVo();
					videoVo.setV_id(rs.getInt("v_id"));
					videoVo.setV_m_id(rs.getInt("v_m_id"));
					videoVo.setV_category(rs.getString("v_category"));
					videoVo.setV_description(rs.getString("v_description"));
					videoVo.setV_video(rs.getString("v_video"));
					videoVo.setV_thumbnail(rs.getString("v_thumbnail"));
					videoVo.setV_title(rs.getString("v_title"));
					videoVo.setV_reg_date(rs.getString("v_reg_date"));
					videoVo.setV_mod_date(rs.getString("v_mod_date"));
					
					String memberSql = "SELECT * FROM minitube_member WHERE m_id = ?";
					List<MemberVo> memberVos = new ArrayList<MemberVo>();
					
					try
					{
						memberVos = jdbcTemplate.query(memberSql, new RowMapper<MemberVo>() {
							@Override
							public MemberVo mapRow(ResultSet m_rs, int m_rowNum) throws SQLException
							{
								MemberVo memberVo = new MemberVo();
								memberVo.setM_id(m_rs.getInt("m_id"));
								memberVo.setM_firstname(m_rs.getString("m_firstname"));
								memberVo.setM_lastname(m_rs.getString("m_lastname"));
								memberVo.setM_mail(m_rs.getString("m_mail"));
								memberVo.setM_pw(m_rs.getString("m_pw"));
								memberVo.setM_profile_img(m_rs.getString("m_profile_img"));			
								return memberVo;
							}
						}, videoVo.getV_m_id());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					videoVo.setV_member(memberVos.get(0));
					return videoVo;
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return videoVos;
	}

	public int updateVideo(String savedThumbnailName, VideoVo videoVo, int v_id) 
	{
		System.out.println("[VideoDao] updateVideo");
		
		System.out.println("savedThumbnailName: " + savedThumbnailName);
		System.out.println("video_title: " + videoVo.getV_title());
		System.out.println("video_description: " + videoVo.getV_description());
		
		
		int result = -1;
		String sql = null;
		if(savedThumbnailName == null)
		{
			System.out.println("savedThumbnailName is NULL");
			sql = "UPDATE minitube_video SET v_title = ?, v_description = ? WHERE v_id = ?";
			result = jdbcTemplate.update(sql, 
				videoVo.getV_title(),
				videoVo.getV_description(),
				v_id
			);
		}
		else
		{
			System.out.println("savedThumbnailName is not NULL");
			sql = "UPDATE minitube_video SET v_title = ?, v_description = ?, v_thumbnail = ? WHERE v_id = ?";
			result = jdbcTemplate.update(sql,
				videoVo.getV_title(),
				videoVo.getV_description(),
				savedThumbnailName,
				v_id
			);
		}
		return result;
	}
}
