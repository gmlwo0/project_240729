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
			@Param("point") double point,	
			@Param("content") String content);
	
	public Post selectPostByPostIdUserId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public void updatePostByPostId(
			@Param("postId") int postId,
			@Param("title") String subject, 
			@Param("point") double point,
			@Param("content") String content); 
	
	public int deletePostByPostId(int postId);
	
    public  void searchPosts(
            @Param("userId") int userId,
            @Param("searchType") String searchType,
            @Param("searchKeyword") String searchKeyword);
}

