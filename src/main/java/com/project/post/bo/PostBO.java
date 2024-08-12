package com.project.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.post.domain.Post;
import com.project.post.mapper.PostMapper;

@Service
public class PostBO {

	@Autowired
	private PostMapper postMapper;
	
	public void addPost(int userId,String userLoginId,
			String subject,String content,double point) {	
	} 
	public Post getPostByPostIdUserId(int userId, int postId) {
		return postMapper.selectPostByPostIdUserId(userId, postId);
	}
	public List<Post> getPostListByUserId(int userId) {
		return postMapper.selectPostListByUserId(userId);
	}
	
}
