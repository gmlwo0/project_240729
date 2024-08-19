package com.project.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.post.domain.Post;

@Mapper
public interface PostMapper {
	public List<Map<String,Object>> testList();
	
	
	public List<Post> selectPostListByUserId(
			@Param("userId") int userId,
			@Param("standardId") Integer standardId,
			@Param("direction") String direction,
			@Param("limit") int limit);
	
	
	public int selectPostIdByUserIdAsSort(
			@Param("userId") int userId,
			@Param("sort") String sort);
	
	public void insertPost(
			@Param("userId") int userId,
			//@Param("movieId") int movieId,
			@Param("title") String title,
			@Param("content") String content,
			@Param("point") double point);	
	
	public Post selectPostByPostIdUserId(
			@Param("userId") int userId,
			@Param("postId") int postId);
}
