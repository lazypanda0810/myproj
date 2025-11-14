package com.example.myproj;

public class Feedback {
    private float rating;
    private String comment;

    public Feedback(float rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
