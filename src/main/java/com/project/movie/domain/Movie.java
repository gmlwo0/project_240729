package com.project.movie.domain;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Movie {
	private int id;
	private String title;
	private String director;
	private String opendate;
	private String imagepath;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
