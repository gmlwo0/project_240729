package com.project.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.comment.domain.Comment;
import com.project.comment.domain.CommentView;

@Mapper
public interface CommentMapper {

	
	public int insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("comments") String comments);
	
	public List<Comment> selectCommentList();
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentById(int id);
	
	public void deleteCommentsByPostId(int postId);
	
	public List<Comment> getCommentsByPostId(int postId);
}
