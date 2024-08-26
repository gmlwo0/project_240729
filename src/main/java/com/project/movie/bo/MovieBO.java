package com.project.movie.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.domain.Movie;
import com.project.movie.mapper.MovieMapper;

@Service
public class MovieBO {

	@Autowired
	private MovieMapper movieMapper;

	// 영화목록
	public List<Movie> getMovieList() {
		return movieMapper.getMovieList();
	}

	// 영화목록 반복문 순회
//	   public void MovieList() {
//	        List<Movie> movieList = getMovieList();
//		for (Movie movie : movieList) {
//	
//		}
//	}
	    // 영화 제목으로 영화 찾기
	    public Movie getMovieByTitle(String title) {
	        return movieMapper.getMovieByTitle(title);
	    }
	    
	    public Movie getMovieById(int movieId) {
	        return movieMapper.getMovieById(movieId);
	    }

	    // 검색어 기반 영화 목록 가져오기
	    public List<Movie> getMovieListByKeyword(String keyword) {
	        return movieMapper.selectMovieListByKeyword(keyword);
	    }
}
