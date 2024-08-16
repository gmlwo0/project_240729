package com.project.timeline.domain;

import java.util.List;

import com.project.comment.domain.CommentView;
import com.project.post.bo.PostBO;
import com.project.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

//view 용 객체
//글 1개와 매핑됨
@ToString
@Data
public class CardView {
	// 글 1개
	private PostBO post;
	
	// 글쓴이 정보
	private UserEntity user;
	
	// 댓글 N개
	private List<CommentView> commentList;
}
