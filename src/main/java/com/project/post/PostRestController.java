package com.project.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
/**
 * 
 * @param userId
 * @param point
 * @param movieId
 * @param content
 * @param session
 * @return
 */
	@PostMapping("/create")
	public Map<String,Object> create(
			@RequestParam("userId")int userId,
			@RequestParam("point")double point,
			@RequestParam("movieId")String movieId,
			@RequestParam("content")String content,
			HttpSession session){
		
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		postBO.addPost(userId, userLoginId, movieId, content, point);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
