package com.project.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.comment.bo.CommentBO;
import com.project.comment.domain.Comment;

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
}
