package com.project.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieController {

	@GetMapping("/movie/movie-list-view")
	public String movieListView(Model model, HttpSession session) {
		
		
		return "movie/movie";
	}
}
