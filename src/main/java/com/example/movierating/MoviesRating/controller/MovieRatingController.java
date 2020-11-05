package com.example.movierating.MoviesRating.controller;

import com.example.movierating.MoviesRating.db.Hibernate_SQLHandler;
import com.example.movierating.MoviesRating.db.JDBC_SQLHandler;
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
        /** JDBC */
        /**
        JDBC_SQLHandler JDBCSQLHandler = new JDBC_SQLHandler();
        Rating rating = JDBCSQLHandler.getRatingById(movieId);
         */

        /** Hibernate */

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        Rating rating = hibernate_sqlHandler.getRatingByMovieId(movieId);
        hibernate_sqlHandler.close();

        return rating;

    }

}
