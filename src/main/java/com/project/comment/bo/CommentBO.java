package com.project.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comment.domain.Comment;
import com.project.comment.domain.CommentView;
import com.project.comment.mapper.CommentMapper;
import com.project.post.bo.PostBO;
import com.project.user.bo.UserBO;
import com.project.user.entity.UserEntity;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;


	
	public void addComment(int postId, int userId, String comments) {
		commentMapper.insertComment(postId, userId, comments);
	}
	// input:글번호    output:List<CommentView>
		public List<Comment> generateCommentListByPostId(int postId) {
			 return commentMapper.selectCommentListByPostId(postId);
			
			// 댓글들 가져옴
		//List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);

//			
//			// 반복문 순회 
//			for (Comment comment : commentList) {
//				CommentView commentView = new CommentView();
//				
//				
//	            // 댓글 정보 설정
//	            commentView.setComment(comment);
//				// 댓글 1개
//				//commentList.set(postId, comment);
//				//commentView.setComment(comment);
//				//commentList.setCommentList(commentList);
//				
//				// 댓글쓰니
//				UserEntity user = userBO.getUserEntityById(comment.getUserId());
//				commentView.setUser(user);
//				
//				//!!!!!! list에 넣기
//				commentList.add(commentView);
//			}
//			
//		return commentList;
		}
		public void deleteCommentById(int id) {
			commentMapper.deleteCommentById(id);
		}
		
		public void deleteCommentsByPostId(int postId) {
			commentMapper.deleteCommentsByPostId(postId);
		}
		
		public List<Comment> getCommentsByPostId(int postId) {
			return commentMapper.selectCommentsByPostId(postId);
		}
}
