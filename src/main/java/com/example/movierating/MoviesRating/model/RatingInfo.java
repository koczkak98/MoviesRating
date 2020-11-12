package com.example.movierating.MoviesRating.model;

import java.util.ArrayList;
import java.util.List;

public class RatingInfo {

    private List<Rating> ratings;

    public RatingInfo() {
        this.ratings = new ArrayList<Rating>();
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRatings (Rating rating)
    {
        this.ratings.add(rating);
    }
}
