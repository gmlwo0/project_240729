package com.project.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	   // 영화 제목으로 검색해서 리뷰 페이지로 리다이렉트하는 기능
    @GetMapping("/movie/review")
    public String searchMovie(
    		@RequestParam("keyword") String keyword,
    		Model model) {
        Movie movie = movieBO.getMovieByTitle(keyword);
        if (movie != null) {
            // 영화가 존재하면 해당 영화의 리뷰 작성 페이지로 리다이렉트
            return "redirect:/post/post-create-view?movieId=" + movie.getId();
        } else {
            // 영화가 존재하지 않으면 에러 메시지와 함께 다시 목록 페이지로 리다이렉트
            model.addAttribute("error_message", "해당 영화가 존재하지 않습니다.");
            return "redirect:/movie/movie-list-view";
        }
    }
}