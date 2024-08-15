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
			String movieId,String content,double point) {	
	} 
	public Post getPostByPostIdUserId(int userId, int postId) {
		return postMapper.selectPostByPostIdUserId(userId, postId);
	}
	// 페이징 정보 필드(limit)
	private static final int POST_MAX_SIZE =3;
	
	// input: 로그인 된 사람의 userId
	// output: List<Post>
	public List<Post> getPostListByUserId(int userId,Integer prevId, Integer nextId) {
		// 게시글 번호 10 9 8 | 7 6 5 | 4 3 2 | 1
		// 만약 4 3 2 페이지에 있을 때
		// 1) 다음: 2보다 작은 3개 DESC
		// 2) 이전: 4보다 큰 3개 ASC => 5 6 7 => BO에서 reverse 7 6 5
		// 3) 페이징 X: 최신순 3 DESC
		Integer standardId = null; // 기준 postId
		String direction = null;
		if (prevId != null) { // 2) 이전
			
		}else if (nextId != null) { // 1) 다음
			standardId = nextId;
			direction = "next";
		}
		// 3) 페이징X, 1) 다음
		return postMapper.selectPostListByUserId(userId, standardId, direction, userId);
		

	}
	
}
