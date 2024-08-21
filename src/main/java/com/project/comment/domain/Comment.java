package com.project.comment.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Comment {
	private int id;
	private int userId;
	private int postId;
	private String comments;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
