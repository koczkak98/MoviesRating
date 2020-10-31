package com.example.movierating.MoviesRating.db;

import com.example.movierating.MoviesRating.model.Rating;

import java.sql.*;

public class MySqlHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PWD = "root";

    public Rating getRatingById (int movieId) throws SQLException {
        Rating rating = new Rating(movieId);

        // Create DB Connection
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

        // Prepare SQL Execution
        Statement stmt = conn.createStatement();

        String sqlSelectAll = "SELECT * FROM movies_rating";
        ResultSet rs = stmt.executeQuery(sqlSelectAll);

        // ON-DEMAND: Iterate over the result
        while(rs.next())
        {
            // movieId Column
            int movieid = rs.getInt("movieId");

            if (movieid == movieId)
            {
                rating.setScores(rs.getFloat("scores"));
                rating.setTotalScore(rs.getDouble("totalScore"));
                rating.setAverages();

                //Exit from rs
                break;
            }
        }


        // Close the ResultSet
        rs.close();
        // Close the Statement
        stmt.close();
        // Close the DB Connection
        conn.close();

        return rating;
    }
}
