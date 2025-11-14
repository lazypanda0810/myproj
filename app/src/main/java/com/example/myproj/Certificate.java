package com.example.myproj;

import java.io.Serializable;

public class Certificate implements Serializable {
    private String courseName;
    private String userName;
    private String date;
    private int score;

    public Certificate(String courseName, String userName, String date, int score) {
        this.courseName = courseName;
        this.userName = userName;
        this.date = date;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }
}
