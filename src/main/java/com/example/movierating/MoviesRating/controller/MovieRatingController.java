package com.example.movierating.MoviesRating.controller;

import com.example.movierating.MoviesRating.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRatingController {

    @GetMapping("/getrating/{movieId}")
    public Rating getRating (
            @PathVariable("movieId") int movieId)
    {

        Rating rating = new Rating(movieId);
        rating.setScores(8);
        rating.setTotalScore(10);
        rating.setAverages();

        return rating;

    }

}
