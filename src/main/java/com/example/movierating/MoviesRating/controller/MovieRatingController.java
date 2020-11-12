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
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieRatingController {

    @GetMapping("/getrating/{movieId}")
    public RatingInfo getRating (
            @PathVariable("movieId") int movieID) throws SQLException
    {
        /** JDBC */
        /**
        JDBC_SQLHandler JDBCSQLHandler = new JDBC_SQLHandler();
        Rating rating = JDBCSQLHandler.getRatingById(movieId);
         */

        /** Hibernate */

        RatingInfo ratingInfo = new RatingInfo();
        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        RestTemplate restTemplate = new RestTemplate();
        Movie movie = restTemplate.getForObject("http://localhost:8081/getmovie/"+movieID, Movie.class);

        List<Integer> ratingIDs = movie.getRatingIDs();
        System.out.println(ratingIDs);


        for (int i = 0; i < ratingIDs.size(); i++)
        {
            hibernate_sqlHandler.open();
            Rating rating = hibernate_sqlHandler.getRatingById(ratingIDs.get(i));
            hibernate_sqlHandler.close();
            ratingInfo.addRatings(rating);
        }


        System.out.println(ratingIDs);
        System.out.println(ratingInfo.getRatings());

        return ratingInfo;

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
