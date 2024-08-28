package com.project.movie.bo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.domain.Movie;
import com.project.movie.mapper.MovieMapper;
import com.project.post.domain.Post;

@Service
public class MovieBO {

	@Autowired
	private MovieMapper movieMapper;
	
	// 페이징 정보 필드(limit)
		private static final int POST_MAX_SIZE = 3;


		// input: 로그인 된 사람의 userId
		// output: List<Post>
		public List<Movie> getMovieListByUserId(int userId, Integer prevId, Integer nextId) {
			// 게시글 번호 10 9 8 | 7 6 5 | 4 3 2 | 1
			// 만약 4 3 2 페이지에 있을 때
			// 1) 다음: 2보다 작은 3개 DESC
			// 2) 이전: 4보다 큰 3개 ASC => 5 6 7 => BO에서 reverse 7 6 5
			// 3) 페이징 X: 최신순 3 DESC
			Integer standardId = null; // 기준 postId
			String direction = null;
			if (prevId != null) { // 2) 이전
				standardId = prevId;
				direction = "prev";

				 List<Movie> movieList = movieMapper.selectMovieListByUserId(userId, standardId, direction, POST_MAX_SIZE);
				// [5,6,7] => [7,6,5]
				Collections.reverse(movieList); // 뒤집고 저장

				return movieList;
			} else if (nextId != null) { // 1) 다음
				standardId = nextId;
				direction = "next";
			}
			// 3) 페이징X, 1) 다음
			return movieMapper.selectMovieListByUserId(userId, standardId, direction, POST_MAX_SIZE);
		}

		// 이전 페이지의 마지막인가?
		public boolean isPrevLastPageByUserId(int userId, int prevId) {
			int maxPostId = movieMapper.selectMovieIdByUserIdAsSort(userId, "DESC");
			return maxPostId == prevId; // 같으면 마지막
		}

		// 다음 페이지의 마지막인가?
		public boolean isNextLastPageByUserId(int userId, int nextId) {
			int minPostId = movieMapper.selectMovieIdByUserIdAsSort(userId, "ASC");
			return minPostId == nextId;
		}

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
