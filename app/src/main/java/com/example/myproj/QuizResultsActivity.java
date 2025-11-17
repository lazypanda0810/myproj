package com.example.myproj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QuizResultsActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extra_score";
    public static final String EXTRA_TOTAL_QUESTIONS = "extra_total_questions";
    public static final String EXTRA_COURSE_NAME = "extra_course_name";
    public static final String EXTRA_COMPLETION_PERCENTAGE = "extra_completion_percentage";

    private static final String PREFS_NAME = "SkillUpPrefs";
    private static final String CERTIFICATES_EARNED_KEY = "certificatesEarned";
    private static final String TOTAL_SCORE_KEY = "totalScore";
    private static final String QUIZ_COUNT_KEY = "quizCount";

    private TextView tvScore;
    private TextView tvMotivationalMessage;
    private Button btnGenerateCertificate;
    private Button btnViewCertificate;
    private Button btnRetakeQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        tvScore = findViewById(R.id.tv_score);
        tvMotivationalMessage = findViewById(R.id.tv_motivational_message);
        btnGenerateCertificate = findViewById(R.id.btn_generate_certificate);
        btnViewCertificate = findViewById(R.id.btn_view_certificate);
        btnRetakeQuiz = findViewById(R.id.btn_retake_quiz);

        int score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        int totalQuestions = getIntent().getIntExtra(EXTRA_TOTAL_QUESTIONS, 0);
        String courseName = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        int completionPercentage = getIntent().getIntExtra(EXTRA_COMPLETION_PERCENTAGE, 0);

        tvScore.setText("Your Score: " + score + "/" + totalQuestions);
        saveQuizScore(score);

        double percentage = totalQuestions > 0 ? ((double) score / totalQuestions) * 100 : 0;

        if (percentage >= 50) {
            tvMotivationalMessage.setText("Congratulations! You've passed the quiz.");
            btnGenerateCertificate.setVisibility(View.VISIBLE);
            saveCertificateCount();

            btnGenerateCertificate.setOnClickListener(v -> {
                Toast.makeText(this, "Certificate generated!", Toast.LENGTH_SHORT).show();
                btnViewCertificate.setVisibility(View.VISIBLE);
                btnGenerateCertificate.setVisibility(View.GONE);
            });

            btnViewCertificate.setOnClickListener(v -> {
                Intent intent = new Intent(QuizResultsActivity.this, CertificateActivity.class);

                // Get student name from SharedPreferences
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                String studentName = prefs.getString("student_name", "Student");

                intent.putExtra(CertificateActivity.EXTRA_STUDENT_NAME, studentName);
                intent.putExtra(CertificateActivity.EXTRA_COURSE_NAME, courseName);
                intent.putExtra(CertificateActivity.EXTRA_COMPLETION_PERCENTAGE, completionPercentage);
                intent.putExtra(CertificateActivity.EXTRA_TEST_SCORE, (int) percentage);
                startActivity(intent);
            });
        } else {
            tvMotivationalMessage.setText("You need to score at least 50% to get a certificate.");
            btnGenerateCertificate.setVisibility(View.GONE);
            btnViewCertificate.setVisibility(View.GONE);
        }

        btnRetakeQuiz.setOnClickListener(v -> finish());
    }

    private void saveQuizScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int totalScore = sharedPreferences.getInt(TOTAL_SCORE_KEY, 0);
        int quizCount = sharedPreferences.getInt(QUIZ_COUNT_KEY, 0);

        sharedPreferences.edit()
                .putInt(TOTAL_SCORE_KEY, totalScore + score)
                .putInt(QUIZ_COUNT_KEY, quizCount + 1)
                .apply();
    }

    private void saveCertificateCount() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int currentCertificates = sharedPreferences.getInt(CERTIFICATES_EARNED_KEY, 0);
        sharedPreferences.edit().putInt(CERTIFICATES_EARNED_KEY, currentCertificates + 1).apply();
    }
}
