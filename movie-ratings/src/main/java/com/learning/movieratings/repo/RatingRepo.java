package com.learning.movieratings.repo;


import org.springframework.data.repository.CrudRepository;
import com.learning.movieratings.model.Rating;


public interface RatingRepo extends CrudRepository<Rating, Integer> {

}
