package com.example.myproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE_ID = "extra_course_id";
    public static final String EXTRA_COURSE_NAME = "extra_course_name";
    public static final String EXTRA_COMPLETION_PERCENTAGE = "extra_completion_percentage";

    private TextView tvQuestionCounter;
    private TextView tvQuestion;
    private RadioGroup rgOptions;
    private List<RadioButton> radioButtons;
    private Button btnNextQuestion;

    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String courseName;
    private int completionPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestionCounter = findViewById(R.id.tv_question_counter);
        tvQuestion = findViewById(R.id.tv_question);
        rgOptions = findViewById(R.id.rg_options);
        RadioButton rbOption1 = findViewById(R.id.rb_option1);
        RadioButton rbOption2 = findViewById(R.id.rb_option2);
        RadioButton rbOption3 = findViewById(R.id.rb_option3);
        RadioButton rbOption4 = findViewById(R.id.rb_option4);
        btnNextQuestion = findViewById(R.id.btn_next_question);

        radioButtons = Arrays.asList(rbOption1, rbOption2, rbOption3, rbOption4);

        int courseId = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
        completionPercentage = getIntent().getIntExtra(EXTRA_COMPLETION_PERCENTAGE, 0);

        if (courseId > 0 && courseId <= MockData.getQuizzes().size() && courseId <= MockData.getCourses().size()) {
            Quiz quiz = MockData.getQuizzes().get(courseId - 1); // courseId is 1-based
            Course course = MockData.getCourses().get(courseId - 1);
            questions = quiz.getQuestions();
            courseName = course.getTitle();
            displayQuestion();
        } else {
            Toast.makeText(this, "Error: Course not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnNextQuestion.setOnClickListener(v -> {
            if (rgOptions.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            checkAnswer();
            currentQuestionIndex++;

            if (currentQuestionIndex < questions.size()) {
                displayQuestion();
            } else {
                finishQuiz();
            }
        });
    }

    private void displayQuestion() {
        rgOptions.clearCheck();
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        tvQuestionCounter.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());
        tvQuestion.setText(currentQuestion.getQuestion());

        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < radioButtons.size(); i++) {
            RadioButton rb = radioButtons.get(i);
            if (i < options.size()) {
                rb.setText(options.get(i));
                rb.setVisibility(View.VISIBLE);
            } else {
                rb.setVisibility(View.GONE);
            }
        }
    }

    private void checkAnswer() {
        RadioButton selectedButton = findViewById(rgOptions.getCheckedRadioButtonId());
        String selectedAnswer = selectedButton.getText().toString();
        int correctAnswerIndex = questions.get(currentQuestionIndex).getCorrectAnswer();
        String correctAnswerText = questions.get(currentQuestionIndex).getOptions().get(correctAnswerIndex);

        if (selectedAnswer.equals(correctAnswerText)) {
            score++;
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(this, QuizResultsActivity.class);
        intent.putExtra(QuizResultsActivity.EXTRA_SCORE, score);
        intent.putExtra(QuizResultsActivity.EXTRA_TOTAL_QUESTIONS, questions.size());
        intent.putExtra(EXTRA_COURSE_NAME, courseName);
        intent.putExtra(QuizResultsActivity.EXTRA_COMPLETION_PERCENTAGE, completionPercentage);
        startActivity(intent);
        finish();
    }
}
