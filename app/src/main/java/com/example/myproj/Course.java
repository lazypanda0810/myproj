package com.example.myproj;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    private String id;
    private String title;
    private String thumbnail;
    private String duration;
    private String difficulty;
    private String overview;
    private List<Lesson> lessons;
    private boolean trending;

    public Course(String id, String title, String thumbnail, String duration, String difficulty, String overview, List<Lesson> lessons, boolean trending) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.difficulty = difficulty;
        this.overview = overview;
        this.lessons = lessons;
        this.trending = trending;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getThumbnail() { return thumbnail; }
    public String getDuration() { return duration; }
    public String getDifficulty() { return difficulty; }
    public String getOverview() { return overview; }
    public List<Lesson> getLessons() { return lessons; }
    public boolean isTrending() { return trending; }
}
