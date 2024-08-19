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
	public String movieListView(Model model, HttpSession session) {
		List<Movie> movieList = movieBO.getMovieList();
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
