package com.project.post.bo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comment.bo.CommentBO;
import com.project.comment.domain.Comment;
import com.project.comment.domain.CommentView;
import com.project.post.domain.Post;
import com.project.post.mapper.PostMapper;
import com.project.user.bo.UserBO;
import com.project.user.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {
	// private Logger log = LoggerFactory.getLogger(PostBO.class);
	// private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private UserBO userBO;
	

	@Autowired
	private CommentBO commentBO;
	
	// 페이징 정보 필드(limit)
	private static final int POST_MAX_SIZE = 3;


	// input: 로그인 된 사람의 userId
	// output: List<Post>
	public List<Post> getPostListByUserId(int userId, Integer prevId, Integer nextId) {
		// 게시글 번호 10 9 8 | 7 6 5 | 4 3 2 | 1
		// 만약 4 3 2 페이지에 있을 때
		// 1) 다음: 2보다 작은 3개 DESC
		// 2) 이전: 4보다 큰 3개 ASC => 5 6 7 => BO에서 reverse 7 6 5
		// 3) 페이징 X: 최신순 3 DESC
		Integer standardId = null; // 기준 postId
		String direction = null;
		if (prevId != null) { // 2) 이전
			standardId = prevId;
			direction = "prev";

			List<Post> postList = postMapper.selectPostListByUserId(userId, standardId, direction, POST_MAX_SIZE);
			// [5,6,7] => [7,6,5]
			Collections.reverse(postList); // 뒤집고 저장

			return postList;
		} else if (nextId != null) { // 1) 다음
			standardId = nextId;
			direction = "next";
		}
		// 3) 페이징X, 1) 다음
		return postMapper.selectPostListByUserId(userId, standardId, direction, POST_MAX_SIZE);
	}

	// 이전 페이지의 마지막인가?
	public boolean isPrevLastPageByUserId(int userId, int prevId) {
		int maxPostId = postMapper.selectPostIdByUserIdAsSort(userId, "DESC");
		return maxPostId == prevId; // 같으면 마지막
	}

	// 다음 페이지의 마지막인가?
	public boolean isNextLastPageByUserId(int userId, int nextId) {
		int minPostId = postMapper.selectPostIdByUserIdAsSort(userId, "ASC");
		return minPostId == nextId;
	}

	// input: userId, postId
	// output: Post or null
	public Post getPostByPostIdUserId(int userId, int postId) {
		return postMapper.selectPostByPostIdUserId(userId, postId);
	}

	// input: 파라미터들
	// output: X
	public void addPost(int userId, String userLoginId, String title ,double point, String content) {

		postMapper.insertPost(userId, title,  point,content);
	}

	// input:파라미터들
	// output:X
	public void updatePostByPostId(
			int userId, String loginId,
			int postId, String title,
			double point, String content) {

		// 기존글 가져온다.(1. 이미지 교체시 삭제하기 위해 2. 업데이트 대상이 있는지 확인)
		Post post = postMapper.selectPostByPostIdUserId(userId, postId);
		if (post == null) {
			log.warn("[글 수정] post is null. userId:{}, postId:{}", userId, postId);
			return;
		}
		postMapper.updatePostByPostId(postId, title, point, content);
	}
	
	
		
		// 글목록을 가져온다. List<PostEntity>
	
	// input:postId, userId
	// output:X
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 기존글 가져오기(이미지 존재 시 삭제를 위해서)
		Post post = postMapper.selectPostByPostIdUserId(userId, postId);
		if (post == null) {
			log.info("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return;
		}
		
		// post db delete
		int rowCount = postMapper.deletePostByPostId(postId);
	}

	
	// List<Post> postList = Post.getPostEntityList(){

	 
	public List<Comment> generateCommentListByPostId(int postId) {
		// 댓글 목록 조회
		return commentBO.getCommentsByPostId(postId);
	//}
	
	
	//public List<CommentView> generateCommentViewList(Integer userId, int postId){
		//List<CommentView> CommentView = new ArrayList<>();
			
		// 댓글 목록 조회
		//List<Comment> comments = commentBO.getCommentsByPostId(postId);



		    // 댓글을 CommentView 객체로 변환하고 리스트에 추가
//		    for (Comment comment : comments) {
//		        CommentView commentView = new CommentView();
//		        commentView.setComment(comment.getComments());
//
//		        // 댓글 작성자 정보 조회
//		        UserEntity user = userBO.getUserEntityById(userId);
//		        commentView.setUser(user);  // 이 부분은 각 댓글 작성자의 정보를 설정하는 것이 아니라 전체 게시글의 작성자 정보를 설정하는 부분입니다. 수정이 필요할 수 있습니다.
//		        
//		        // 대댓글은 제외하고 댓글만 리스트에 추가
//		        commentViewList.add(commentView);
//		    }

			
	//		return CommentView;
	}
}