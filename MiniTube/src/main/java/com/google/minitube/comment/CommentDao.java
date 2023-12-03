package com.google.minitube.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.minitube.member.MemberVo;

@Component
public class CommentDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int InsertComment(CommentVo commentVo, MemberVo memberVo, int v_id)
	{
		System.out.println("[InsertComment] InsertComment(commentVo)");
		
		String sql = "INSERT INTO minitube_comment";
		sql += "(c_m_id, c_v_id, c_content, c_reg_date, c_mod_date) ";
		sql += "VALUES (?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		try
		{
			result = jdbcTemplate.update(sql,
					memberVo.getM_id(),
					v_id,
					commentVo.getC_content()
			);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public List<CommentVo> SelectAllComments(int v_id)
	{
		System.out.println("[SelectAllComments] SelectAllComments(int v_id)");
		String sql = "SELECT * FROM minitube_comment WHERE c_v_id = ? AND c_c_id IS NULL";
		
		List<CommentVo> commentVos = new ArrayList<CommentVo>();
		try
		{
			commentVos = jdbcTemplate.query(sql, new RowMapper<CommentVo>() {
				@Override
				public CommentVo mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					CommentVo commentVo = new CommentVo();
					commentVo.setC_id(rs.getInt("c_id"));
					commentVo.setC_m_id(rs.getInt("c_m_id"));
					commentVo.setC_c_id(rs.getInt("c_c_id"));
					commentVo.setC_v_id(rs.getInt("c_v_id"));
					commentVo.setC_content(rs.getString("c_content"));
					commentVo.setC_reg_date(rs.getString("c_reg_date"));
					commentVo.setC_mod_date(rs.getString("c_mod_date"));
					
					List<CommentVo> childCommentVos = new ArrayList<CommentVo>();
					String childCommentSql = "SELECT * FROM minitube_comment WHERE c_c_id = ?";
					try
					{
						childCommentVos = jdbcTemplate.query(childCommentSql, new RowMapper<CommentVo>() {
							@Override
							public CommentVo mapRow(ResultSet rs2, int rowNum2) throws SQLException
							{
								CommentVo childCommentVo = new CommentVo();
								
								childCommentVo.setC_id(rs2.getInt("c_id"));
								childCommentVo.setC_c_id(rs2.getInt("c_c_id"));
								childCommentVo.setC_m_id(rs2.getInt("c_m_id"));
								childCommentVo.setC_v_id(rs2.getInt("c_v_id"));
								childCommentVo.setC_content(rs2.getString("c_content"));
								childCommentVo.setC_reg_date(rs2.getString("c_reg_date"));
								childCommentVo.setC_mod_date(rs2.getString("c_mod_date"));
								
								return childCommentVo;
							}
							
						}, commentVo.getC_id());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					commentVo.setComments(childCommentVos);
					return commentVo;
				}
			}, v_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return commentVos;
	}
}
