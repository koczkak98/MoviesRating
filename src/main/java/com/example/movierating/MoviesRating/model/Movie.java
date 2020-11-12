package com.example.movierating.MoviesRating.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class Movie {

    private Integer movieID;

    private String title;

    private String category;

    private String description;

    private int ageLimit;

    private List<Integer> ratingIDs;



    public Movie() {
        this.ratingIDs = new ArrayList<Integer>();
    }

    public Movie(Integer movieID) {
        super();
        this.movieID = movieID;
        this.ratingIDs = new ArrayList<Integer>();
    }


    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<Integer> getRatingIDs() {
        return ratingIDs;
    }

    public void setRatingIDs(List<Integer> ratingIDs) {
        this.ratingIDs = ratingIDs;
    }
}
