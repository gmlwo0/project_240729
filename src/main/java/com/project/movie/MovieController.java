package com.project.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.movie.bo.MovieBO;
import com.project.movie.domain.Movie;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieController {

	@Autowired
	private MovieBO movieBO;
	
	
	@GetMapping("/movie/movie-list-view")
	public String movieListView(Model model, HttpSession session) {
		
		List<Movie> movieList = movieBO.getMovieList();
		
		model.addAttribute("movieList", movieList);
		
		return "movie/movie";
	}
}
