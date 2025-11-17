package com.example.myproj;

import java.io.Serializable;

public class Lesson implements Serializable {
    private final String id;
    private final String title;
    private final String duration;
    private boolean completed;
    private final String pointers;
    private final String theory;
    private final String codeExamples;

    public Lesson(String id, String title, String duration, String pointers, String theory, String codeExamples) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.completed = false;
        this.pointers = pointers;
        this.theory = theory;
        this.codeExamples = codeExamples;
    }

    public Lesson(String id, String title, String duration) {
        this(id, title, duration, "", "", "");
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

    public String getPointers() {
        return pointers;
    }

    public String getTheory() {
        return theory;
    }

    public String getCodeExamples() {
        return codeExamples;
    }
}
