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

	
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}
	// input:글번호    output:List<CommentView>
		public List<CommentView> generateCommentViewListByPostId(int postId) {
			List<CommentView> commentViewList = new ArrayList<>();
			
			
			// 댓글들 가져옴
			List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
			
			if (commentList == null || commentList.isEmpty()) {
				return commentViewList; // 비어있는 리스트 반환
			}
			
			// 반복문 순회 => Comment -> CommentView   => list에 담음
			for (Comment comment : commentList) {
				CommentView commentView = new CommentView();
				
				// 댓글 1개
				commentView.setComment(comment);
				
				// 댓글쓰니
				UserEntity user = userBO.getUserEntityById(comment.getUserId());
				commentView.setUser(user);
				
				//!!!!!! list에 넣기
				commentViewList.add(commentView);
			}
			
			return commentViewList;
		}
}
