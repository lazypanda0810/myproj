package com.example.myproj;

import java.io.Serializable;
import java.util.List;

public class QuizQuestion implements Serializable {
    private String id;
    private String question;
    private List<String> options;
    private int correctAnswer;

    public QuizQuestion(String id, String question, List<String> options, int correctAnswer) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
