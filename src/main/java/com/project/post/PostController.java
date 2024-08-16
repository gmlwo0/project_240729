package com.project.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.comment.bo.CommentBO;
import com.project.comment.domain.Comment;
import com.project.comment.mapper.CommentMapper;
import com.project.post.bo.PostBO;
import com.project.post.domain.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostBO postBO;

	
	@Autowired
	private CommentMapper commentMapper;
/**
 * 
 * @param prevIdparam
 * @param nextIdparam
 * @param session
 * @param model
 * @return
 */
	@GetMapping("/post-list-view")
	public String postListView(
			@RequestParam(value="prevId",required = false) Integer prevIdparam,
			@RequestParam(value="nextvId",required = false) Integer nextIdparam,
			HttpSession session, Model model) {
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			// 비로그인이면 로그인 페이지로 이동
			return "redirect:/user/sign-in-view";
		}
		
		// DB 조회 - 글 목록
		List<Post> postList = postBO.getPostListByUserId(userId,prevIdparam,nextIdparam);
		int prevId = 0;
		int nextId = 0;
		if (postList.isEmpty() == false) { // 글목록이 비어있지 않을 때 페이징 정보 세팅
			prevId = postList.get(0).getId(); // 첫번째칸 id
			nextId = postList.get(postList.size()-1).getId(); // 마지막칸 id
		}
		
		// 모델에 담기
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("postList", postList);
		
		return "post/postList";
	}
	
	/**
	 * 
	 * @return
	 */
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
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		// model에 담기
		model.addAttribute("post", post);
		model.addAttribute("commentList", commentList);	
		// 화면 이동
		return "post/postDetail";
	}
}
