package com.project.movie.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.mapper.MovieMapper;

@Service
public class MovieBO {
		
	@Autowired
	private MovieMapper movieMapper;
	
	
			// 영화목록
			public List<Movie> = getMovieList(){
				return movieMapper.selectMovieList();
			}
			
			// 영화목록 반복문 순회
			for (Movie : movieList) {
				Movie movie = new movieList();
				
			}
}
		