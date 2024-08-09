package com.project.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper	
public interface MovieMapper {

	public List<Movie> selectMovieList();
}
