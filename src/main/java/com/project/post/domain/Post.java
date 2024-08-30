package com.project.post.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Post {

	
	private int id;
	private int userId;
	private int movieId;
	private String title;
	private String content;
	private double point;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
