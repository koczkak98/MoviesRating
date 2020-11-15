package com.example.movierating.MoviesRating.model;

import javax.persistence.*;

@Entity
@Table(name = "movies_rating")
public class Rating {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @Column
    private double scores;

    @Column(name = "totalScore")
    private double totalScore;

    @Transient
    private double averages = ((this.scores / this.totalScore)) * 100;


    public Rating() {
    }

    public Rating(Integer id) {
        this.ratingId = id;
    }


    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }


    public double getAverages() {
        return averages;
    }

    public void setAverages() {
        this.averages = ((this.scores / this.totalScore)) * 100;
    }


}
