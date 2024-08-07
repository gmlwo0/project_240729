package com.project.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	public PostEntity.addPost(int userId, String userLoginId,
			double point, String subject, String content)
	
		postMapper.insertPost(userId,point,subject,content);
}
