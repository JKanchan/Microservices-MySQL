package com.learning.moviecatalog.resources;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learning.moviecatalog.model.CatalogItem;
import com.learning.moviecatalog.model.Movie;
import com.learning.moviecatalog.model.Rating;
import com.learning.moviecatalog.model.UserRating;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		//for each movie id ,call the movie info service and get details
		//get all the movies
		//put all together
		UserRating ratings = restTemplate.getForObject("http://movie-ratings/ratings/user/"+userId, UserRating.class);
		
		
		return ratings.getRatings().stream()
                .map(rating -> {
                	Movie movie =restTemplate.getForObject("http://movie-info/movies/"+rating.getMovieId(), Movie.class);		 
                    return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
                	 })
                .collect(Collectors.toList());
	}

}
