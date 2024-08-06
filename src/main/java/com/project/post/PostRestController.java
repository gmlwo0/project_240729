package com.project.post;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String,Object> create(
			@RequestParam("userId")int userId,
			@RequestParam("point")double point,
			@RequestParam("subject")String subject,
			@RequestParam("content")String content,
			HttpSession session){
		
	}
}
