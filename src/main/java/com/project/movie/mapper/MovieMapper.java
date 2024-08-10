package com.project.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.movie.domain.Movie;

@Mapper	
public interface MovieMapper {

	public List<Movie> getMovieList();
}
