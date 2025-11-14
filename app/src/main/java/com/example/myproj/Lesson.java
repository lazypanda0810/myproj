package com.example.myproj;

import java.io.Serializable;

public class Lesson implements Serializable {
    private final String id;
    private final String title;
    private final String duration;
    private boolean completed;

    public Lesson(String id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.completed = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
