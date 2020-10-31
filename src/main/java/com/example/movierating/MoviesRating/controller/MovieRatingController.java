package com.example.movierating.MoviesRating.controller;

import com.example.movierating.MoviesRating.db.MySqlHandler;
import com.example.movierating.MoviesRating.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MovieRatingController {

    @GetMapping("/getrating/{movieId}")
    public Rating getRating (
            @PathVariable("movieId") int movieId) throws SQLException
    {
        MySqlHandler mySqlHandler = new MySqlHandler();
        Rating rating = mySqlHandler.getRatingById(movieId);

        return rating;

    }

}
