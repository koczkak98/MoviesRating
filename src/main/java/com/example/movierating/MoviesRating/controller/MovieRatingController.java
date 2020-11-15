package com.example.movierating.MoviesRating.controller;

import com.example.movierating.MoviesRating.db.Hibernate_SQLHandler;
import com.example.movierating.MoviesRating.db.JDBC_SQLHandler;
import com.example.movierating.MoviesRating.model.Movie;
import com.example.movierating.MoviesRating.model.Rating;
import com.example.movierating.MoviesRating.model.RatingInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@RestController
public class MovieRatingController {

    @GetMapping("/getrating/{ratingId}")
    public Rating getRating (
            @PathVariable("ratingId") int ratingID) throws SQLException
    {

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Rating rating = hibernate_sqlHandler.getRatingById(ratingID);
        rating.setAverages();
        hibernate_sqlHandler.close();


        return rating;

    }


    @GetMapping("/deleterating/{movieid}")
    public RatingInfo deleteMovieById (
            @PathVariable("movieid") int movieID)
    {
        RatingInfo rating = new RatingInfo();

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        rating.setRatings(hibernate_sqlHandler.deleteRatingByMovieId(movieID));
        hibernate_sqlHandler.close();

        return rating;
    }

}
