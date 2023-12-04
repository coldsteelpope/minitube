package com.google.minitube.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.minitube.video.VideoVo;

@Component
public class MemberDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public MemberVo SelectMember(int m_id)
	{
		System.out.println("[MemberDao] SelectMember(m_id)");
		String sql = "SELECT * FROM minitube_member WHERE m_id = ?";
		List<MemberVo> memberVos = new ArrayList<MemberVo>();
		
		try
		{
			memberVos = jdbcTemplate.query(sql, new RowMapper<MemberVo>() {
				@Override
				public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					MemberVo memberVo = new MemberVo();
					memberVo.setM_id(rs.getInt("m_id"));
					memberVo.setM_firstname(rs.getString("m_firstname"));
					memberVo.setM_lastname(rs.getString("m_lastname"));
					memberVo.setM_mail(rs.getString("m_mail"));
					memberVo.setM_pw(rs.getString("m_pw"));
					memberVo.setM_profile_img(rs.getString("m_profile_img"));
					
					String videoSql = "SELECT * FROM minitube_video WHERE v_m_id = ?";
					List<VideoVo> videoVos = new ArrayList<VideoVo>();
					try
					{
						videoVos = jdbcTemplate.query(videoSql, new RowMapper<VideoVo>() {
							@Override
							public VideoVo mapRow(ResultSet rs2, int rowNum2) throws SQLException
							{
								VideoVo videoVo = new VideoVo();
								videoVo.setV_id(rs2.getInt("v_id"));
								videoVo.setV_m_id(rs2.getInt("v_m_id"));
								videoVo.setV_category(rs2.getString("v_category"));
								videoVo.setV_description(rs2.getString("v_description"));
								videoVo.setV_video(rs2.getString("v_video"));
								videoVo.setV_thumbnail(rs2.getString("v_thumbnail"));
								videoVo.setV_title(rs2.getString("v_title"));
								videoVo.setV_reg_date(rs2.getString("v_reg_date"));
								videoVo.setV_mod_date(rs2.getString("v_mod_date"));
								return videoVo;
							}
						}, memberVo.getM_id());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					memberVo.setM_videos(videoVos);
					return memberVo;
				}
			}, m_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return memberVos.size() > 0 ? memberVos.get(0) : null;
	}
}
