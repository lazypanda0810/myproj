package com.example.myproj;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private String courseId;
    private List<QuizQuestion> questions;

    public Quiz(String courseId, List<QuizQuestion> questions) {
        this.courseId = courseId;
        this.questions = questions;
    }

    public String getCourseId() {
        return courseId;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }
}
