package com.project.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.movie.domain.Movie;

@Mapper	
public interface MovieMapper {
	
	// 영화 목록 가져오기
	public List<Movie> getMovieList();
	
    // 영화 제목으로 영화 가져오기
    Movie getMovieByTitle(
    		@Param("title") String title);
    
    // 영화 ID로 영화 가져오기
    Movie getMovieById(@Param("movieId") int movieId);
    
    // 검색어 기반 영화 목록 가져오기
    List<Movie> selectMovieListByKeyword(
    		@Param("keyword") String keyword);
}
