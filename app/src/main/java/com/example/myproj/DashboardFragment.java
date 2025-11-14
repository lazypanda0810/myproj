package com.example.myproj;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.Set;

public class DashboardFragment extends Fragment {

    private static final String PREFS_NAME = "SkillUpPrefs";
    private static final String CURRENT_STREAK_KEY = "currentStreak";
    private static final String SKILL_POINTS_KEY = "skillPoints";
    private static final String CERTIFICATES_EARNED_KEY = "certificatesEarned";
    private static final String COURSES_VIEWED_KEY = "courses_viewed";
    private static final String TOTAL_SCORE_KEY = "totalScore";
    private static final String QUIZ_COUNT_KEY = "quizCount";

    private TextView tvCoursesViewed;
    private TextView tvCertificatesEarned;
    private TextView tvAvgQuizScore;
    private TextView tvCurrentStreak;
    private TextView tvSkillPointsLarge;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        tvCoursesViewed = view.findViewById(R.id.tv_courses_viewed);
        tvCertificatesEarned = view.findViewById(R.id.tv_certificates_earned);
        tvAvgQuizScore = view.findViewById(R.id.tv_avg_quiz_score);
        tvCurrentStreak = view.findViewById(R.id.tv_current_streak);
        tvSkillPointsLarge = view.findViewById(R.id.tv_skill_points_large);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAndDisplayStats();
    }

    private void loadAndDisplayStats() {
        int currentStreak = sharedPreferences.getInt(CURRENT_STREAK_KEY, 0);
        int skillPoints = sharedPreferences.getInt(SKILL_POINTS_KEY, 0);
        int certificatesEarned = sharedPreferences.getInt(CERTIFICATES_EARNED_KEY, 0);
        Set<String> coursesViewed = sharedPreferences.getStringSet(COURSES_VIEWED_KEY, new HashSet<>());
        int totalScore = sharedPreferences.getInt(TOTAL_SCORE_KEY, 0);
        int quizCount = sharedPreferences.getInt(QUIZ_COUNT_KEY, 0);

        tvCoursesViewed.setText(String.valueOf(coursesViewed.size()));

        if (quizCount > 0) {
            // Assuming each quiz has 5 questions
            int avgScorePercentage = (totalScore * 100) / (quizCount * 5);
            tvAvgQuizScore.setText(avgScorePercentage + "%");
        } else {
            tvAvgQuizScore.setText("0%");
        }

        tvCurrentStreak.setText(String.valueOf(currentStreak));
        tvSkillPointsLarge.setText("🏆 " + skillPoints);
        tvCertificatesEarned.setText(String.valueOf(certificatesEarned));
    }
}
