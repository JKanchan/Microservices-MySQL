package com.learning.movieinfo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.movieinfo.model.Movie;
import com.learning.movieinfo.repo.MovieRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Optional;
@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	 @Autowired 
	  private MovieRepo movieRepo;

	@RequestMapping("/{movieId}")
	public Optional<Movie>getMovieInfo(@PathVariable("movieId")int movieId) {
		return movieRepo.findById(movieId);
	}
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam int movieId ,@RequestParam String name
	      , @RequestParam String description) {
	   
	    Movie n = new Movie();
	    n.setMovieId(movieId);
	    n.setName(name);
	    n.setDescription(description);;
	    movieRepo.save(n);
	    return "Saved";
	}
	 @GetMapping(path="/all")
	  public @ResponseBody Iterable<Movie> getAllMovies() {
	    // This returns a JSON or XML with the users
	    return movieRepo.findAll();
	  }
}
