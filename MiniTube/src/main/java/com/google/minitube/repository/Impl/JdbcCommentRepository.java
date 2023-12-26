package com.google.minitube.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.minitube.constants.CommentSql;
import com.google.minitube.dto.Comment;
import com.google.minitube.dto.Member;
import com.google.minitube.repository.CommentRepository;
import com.google.minitube.repository.MemberRepository;

@Repository
public class JdbcCommentRepository implements CommentRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public JdbcCommentRepository(MemberRepository memberRepository)
	{
		this.memberRepository = memberRepository;
	}
	
	@Override
	public long save(Comment comment, Member member) 
	{
		System.out.println("[JdbcCommentRepository] save");
		try
		{	
			int result = jdbcTemplate.update(CommentSql.INSERT_COMMENT,
					member.getM_id(),
					comment.getC_v_id(),
					comment.getC_content()
			);
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long saveChild(Comment comment, Member member) 
	{
		System.out.println("[JdbcCommentRepository] saveChild");
		try
		{
			int result = jdbcTemplate.update(CommentSql.INSERT_CHILD_COMMENT, 
					comment.getC_v_id(),
					member.getM_id(),
					comment.getC_c_id(),
					comment.getC_content()
			);
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long delete(int c_id) 
	{
		System.out.println("[JdbcCommentRepository] delete");
		
		try
		{
			List<Comment> childComments = jdbcTemplate.query(CommentSql.SELECT_CHILD_COMMENTS_BY_PARENT_ID, commentRowMapper(), c_id);
			for(Comment childComment : childComments)
			{
				jdbcTemplate.update(CommentSql.DELETE_COMMENT, childComment.getC_id());
			}
			int result = jdbcTemplate.update(CommentSql.DELETE_COMMENT, c_id);
			return result;
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public long deleteAllByCVId(int c_v_id) 
	{
		System.out.println("[JdbcCommentRepository] deleteByCVId");
		try
		{
			long deleteChildComments = jdbcTemplate.update(CommentSql.DELETE_CHILD_COMMENTS_BY_C_V_ID, c_v_id);
			long deleteParentComments = jdbcTemplate.update(CommentSql.DELETE_PARENT_COMMENTS_BY_C_V_ID, c_v_id);
		
			return deleteParentComments;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	@Override
	public long deleteChild(int c_id) 
	{
		System.out.println("[JdbcCommentRepository] deleteChild");
		
		try
		{
			int result = jdbcTemplate.update(CommentSql.DELETE_COMMENT, c_id);
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long edit(int c_id, String c_content) 
	{
		System.out.println("[JdbcCommentRepository] edit");
		try
		{
			int result = jdbcTemplate.update(CommentSql.UPDATE_COMMENT, c_content, c_id);
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Comment> findAllByVId(int v_id) 
	{
		return jdbcTemplate.query(CommentSql.SELECT_ALL_COMMENTS, commentRowMapper(), v_id);
	}
	
	@Override
	public List<Comment> findAllByCCId(int c_c_id) 
	{
		return jdbcTemplate.query(CommentSql.SELECT_CHILD_COMMENTS_BY_PARENT_ID, commentRowMapper(), c_c_id);
	}

	private RowMapper<Comment> commentRowMapper()
	{
		return (rs, rosNum) -> {
			Comment comment = new Comment();
			comment.setC_id(rs.getInt("c_id"));
			comment.setC_m_id(rs.getInt("c_m_id"));
			comment.setC_c_id(rs.getInt("c_c_id"));
			comment.setC_v_id(rs.getInt("c_v_id"));
			comment.setC_content(rs.getString("c_content"));
			comment.setC_reg_date(rs.getString("c_reg_date"));
			comment.setC_mod_date(rs.getString("c_mod_date"));
			
			Member member = memberRepository.findById(rs.getInt("c_m_id"));
			comment.setC_member(member);

			List<Comment> childComments = findAllByCCId(comment.getC_id());
			comment.setComments(childComments);
			
			return comment;
		};
	}




	
}
