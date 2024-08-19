package com.project.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			Model model,HttpSession session) {
		List<Movie> movieList = movieBO.getMovieList();
		model.addAttribute("movieList", movieList);
		return "movie/movieDetail";
	}

	@GetMapping("/movie-list-view")
	public String movieListView(
			@RequestParam(value = "keyword", required = false) String keyword, 
			Model model,HttpSession session) {
		List<Movie> movieList = movieBO.getMovieList();
		model.addAttribute("movieList", movieList);
		
		Movie movie = movieBO.getMovieByTitle(keyword);
		if (movie != null) {
			// 영화가 존재하면 해당 영화의 정보 페이지로
			return "redirect:/movie-detail-view?movieId=" + movie.getId();
		} else {
			// 영화가 존재하지 않으면 다시 목록 페이지로
			return "/movie/movie";
		}
	}
	
	// 영화 제목으로 검색해서 리뷰 페이지로 리다이렉트하는 기능
//	@GetMapping("/detail-view")
//	public String searchMovie(
//			@RequestParam(value = "keyword", required = false) String keyword,
//			Model model) {
//		Movie movie = movieBO.getMovieByTitle(keyword);
//		if (movie != null) {
//			// 영화가 존재하면 해당 영화의 리뷰 작성 페이지로 리다이렉트
//			return "redirect:/movie/movie-detail-view?movieId=" + movie.getId();
//		} else {
//			// 영화가 존재하지 않으면 다시 목록 페이지로 리다이렉트
//			return "redirect:/movie/movie-list-view";
//		}
//	}
}