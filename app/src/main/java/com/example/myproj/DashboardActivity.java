package com.example.myproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SkillUpPrefs";
    private static final String CURRENT_STREAK_KEY = "currentStreak";
    private static final String SKILL_POINTS_KEY = "skillPoints";
    private static final String CERTIFICATES_EARNED_KEY = "certificatesEarned";

    private TextView tvCoursesViewed;
    private TextView tvCertificatesEarned;
    private TextView tvAvgQuizScore;
    private TextView tvCurrentStreak;
    private TextView tvSkillPointsLarge;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Initialize views
        tvCoursesViewed = findViewById(R.id.tv_courses_viewed);
        tvCertificatesEarned = findViewById(R.id.tv_certificates_earned);
        tvAvgQuizScore = findViewById(R.id.tv_avg_quiz_score);
        tvCurrentStreak = findViewById(R.id.tv_current_streak);
        tvSkillPointsLarge = findViewById(R.id.tv_skill_points_large);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAndDisplayStats();
    }

    private void loadAndDisplayStats() {
        int currentStreak = sharedPreferences.getInt(CURRENT_STREAK_KEY, 0);
        int skillPoints = sharedPreferences.getInt(SKILL_POINTS_KEY, 0);
        int certificatesEarned = sharedPreferences.getInt(CERTIFICATES_EARNED_KEY, 0);

        // TODO: Implement logic for these stats later
        tvCoursesViewed.setText("0");
        tvAvgQuizScore.setText("0%");

        tvCurrentStreak.setText(String.valueOf(currentStreak));
        tvSkillPointsLarge.setText("🏆 " + skillPoints);
        tvCertificatesEarned.setText(String.valueOf(certificatesEarned));
    }
}
