package com.project.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.movie.bo.MovieBO;
import com.project.movie.domain.Movie;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieBO movieBO;

	@GetMapping("/movie-detail-view")
	public String movieDetailView(
			@RequestParam("movieId") int movieId,
			Model model, HttpSession session) {
		List<Movie> movieList = movieBO.getMovieList();
		 Movie movie = movieBO.getMovieById(movieId);
		model.addAttribute("movieList", movieList);
		return "movie/movieDetail";
	}

	@GetMapping("/movie-list-view")
	public String movieListView(
			@RequestParam(value="prevId",required = false) Integer prevIdparam,
			@RequestParam(value="nextId",required = false) Integer nextIdparam,
			Model model, HttpSession session) { 
		List<Movie> movieList = movieBO.getMovieList();
		// List<Movie> movieList = movieBO.getPostListByUserId(userId,prevIdparam,nextIdparam);
		int prevId = 0;
		int nextId = 0;
		if (movieList.isEmpty() == false) { // 글목록이 비어있지 않을 때 페이징 정보 세팅
			prevId = movieList.get(0).getId(); // 첫번째칸 id
			nextId = movieList.get(movieList.size()-1).getId(); // 마지막칸 id
			
			// 이전 방향의 끝인가? 그러면 0
			// prevId와 테이블의 제일 큰 숫자와 같으면 이전의 끝페이지
			if (movieBO.isPrevLastPage(prevId)) {
				prevId = 0;
			}
			
			// 다음 방향의 끝인가? 그러면 0
			// nextId와 테이블의 제일 작은 숫자가 같으면 다음의 끝페이지
			if (movieBO.isNextLastPage(nextId)) {
				nextId = 0;
			}
		}
		
		// 모델에 담기
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("movieList", movieList);
		return "/movie/movie";
	}

	@ResponseBody
	@PostMapping("/movie-list-view")
	public Map<String, Object> movieListView(
			@RequestParam(value = "keyword", required = false) String keyword,
			Model model, HttpSession session) {
		List<Movie> movieList = movieBO.getMovieList();
		model.addAttribute("movieList", movieList);

		Movie movie = movieBO.getMovieByTitle(keyword);
		Map<String, Object> result = new HashMap<>();
		if (movie != null) {
			result.put("code", 200);
			result.put("movieId", movie.getId());
		} else {
			result.put("code", 500);
			result.put("error_message", "일치하는 영화가 없습니다.");
		}

		return result;
	}

}
