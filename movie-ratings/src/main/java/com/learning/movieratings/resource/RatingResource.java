package com.learning.movieratings.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.movieratings.model.Rating;
import com.learning.movieratings.model.UserRating;
import com.learning.movieratings.repo.RatingRepo;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
	 @Autowired 
	  private RatingRepo ratingRepo;

	@RequestMapping("/{movieId}")
	public Optional<Rating>getRating(@PathVariable("movieId")int movieId) {
		return ratingRepo.findById(movieId);
	}
	 @RequestMapping("/user/{userId}") 
	 public UserRating getUserRatings(@PathVariable("userId") int userId) 
	 {
		 List<Rating> ratings = 
				 Arrays.asList(
					new Rating(1,5),
					new Rating(2,5)
					);
		 UserRating userRating  = new UserRating(); 
		 userRating.setRatings(ratings);
		 return userRating;
	 
	 }
	 
	 @GetMapping(path="/all")
	  public @ResponseBody Iterable<Rating> getAllratings() {
	    // This returns a JSON or XML with the users
	    return ratingRepo.findAll();
	  }
	

}
