package com.project.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.post.mapper.PostMapper;

@Service
public class PostBO {

	@Autowired
	private PostMapper postMapper;
	
	public void addPost(int userId,String userLoginId,
			String subject,String content,double point) {	
	} 
	
}
