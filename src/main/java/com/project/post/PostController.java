package com.project.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.post.bo.PostBO;
import com.project.post.domain.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostBO postBO;


/**
 * 
 * @param model
 * @param session
 * @param userId 
 * @return
 */
	@GetMapping("/post-list-view")
	public String postListView(
		@RequestParam("userId") int userId,
		Model model, HttpSession session) {
		List<Post> postList = postBO.getPostListByUserId(userId);
		model.addAttribute("postList", postList);
		return "post/postList";
	}

	@GetMapping("/post-create-view")
	public String postCreateView() {
		return "post/postCreate";
	}

	/**
	 * 
	 * @param postId
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/post-detail-view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			Model model, HttpSession session) {

		// db 조회 - userId,postId
		int userId = (int) session.getAttribute("userId");
		Post post = postBO.getPostByPostIdUserId(userId,postId);

		// model에 담기
		model.addAttribute("post", post);
		// 화면 이동
		return "post/postDetail";
	}
}
