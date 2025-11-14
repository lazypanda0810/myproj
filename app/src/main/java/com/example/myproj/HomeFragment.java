package com.example.myproj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private static final String PREFS_NAME = "SkillUpPrefs";
    private static final String CHALLENGE_COMPLETED_DATE_KEY = "challengeCompletedDate";
    private static final String LAST_STREAK_UPDATE_DATE_KEY = "lastStreakUpdateDate";
    private static final String CURRENT_STREAK_KEY = "currentStreak";
    private static final String SKILL_POINTS_KEY = "skillPoints";

    private TextView tvMotivationalQuote;
    private TextView tvStreakCounter;
    private TextView tvSkillPoints;
    private TextView tvBadgeProgression;
    private TextView tvDailyChallengeDescription;
    private CardView cvDailyChallenge;
    private Button btnMarkAsComplete;
    private Button btnStartLearning;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        tvMotivationalQuote = view.findViewById(R.id.tv_motivational_quote);
        tvStreakCounter = view.findViewById(R.id.tv_streak_counter);
        tvSkillPoints = view.findViewById(R.id.tv_skill_points);
        tvBadgeProgression = view.findViewById(R.id.tv_badge_progression);
        cvDailyChallenge = view.findViewById(R.id.cv_daily_challenge);
        tvDailyChallengeDescription = view.findViewById(R.id.tv_daily_challenge_description);
        btnMarkAsComplete = view.findViewById(R.id.btn_mark_as_complete);
        btnStartLearning = view.findViewById(R.id.btn_start_learning);

        setMotivationalQuote();
        setupDailyChallenge();
        updateStreakAndPointsDisplay();

        btnStartLearning.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), CoursesActivity.class));
        });

        return view;
    }

    private void setMotivationalQuote() {
        String[] quotes = getResources().getStringArray(R.array.motivational_quotes);
        int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int quoteIndex = dayOfYear % quotes.length;
        tvMotivationalQuote.setText(quotes[quoteIndex]);
    }

    private void setupDailyChallenge() {
        String[] challenges = getResources().getStringArray(R.array.daily_challenges);
        int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int challengeIndex = dayOfYear % challenges.length;
        tvDailyChallengeDescription.setText(challenges[challengeIndex]);

        int lastCompletedDay = sharedPreferences.getInt(CHALLENGE_COMPLETED_DATE_KEY, -1);

        if (lastCompletedDay == dayOfYear) {
            btnMarkAsComplete.setText("Completed");
            btnMarkAsComplete.setEnabled(false);
        } else {
            btnMarkAsComplete.setOnClickListener(v -> {
                markChallengeAsComplete();
            });
        }
    }

    private void markChallengeAsComplete() {
        int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        sharedPreferences.edit().putInt(CHALLENGE_COMPLETED_DATE_KEY, dayOfYear).apply();

        btnMarkAsComplete.setText("Completed");
        btnMarkAsComplete.setEnabled(false);

        updateStreak();
        addSkillPoints(10);
        updateStreakAndPointsDisplay();

        Toast.makeText(getActivity(), "Challenge Completed!", Toast.LENGTH_SHORT).show();
    }

    private void updateStreak() {
        int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int lastStreakUpdateDay = sharedPreferences.getInt(LAST_STREAK_UPDATE_DATE_KEY, -1);
        int currentStreak = sharedPreferences.getInt(CURRENT_STREAK_KEY, 0);

        if (lastStreakUpdateDay == dayOfYear - 1) {
            currentStreak++;
        } else if (lastStreakUpdateDay != dayOfYear) {
            currentStreak = 1;
        }

        sharedPreferences.edit()
                .putInt(CURRENT_STREAK_KEY, currentStreak)
                .putInt(LAST_STREAK_UPDATE_DATE_KEY, dayOfYear)
                .apply();
    }

    private void addSkillPoints(int pointsToAdd) {
        int currentPoints = sharedPreferences.getInt(SKILL_POINTS_KEY, 0);
        currentPoints += pointsToAdd;
        sharedPreferences.edit().putInt(SKILL_POINTS_KEY, currentPoints).apply();
    }

    private void updateStreakAndPointsDisplay() {
        int currentStreak = sharedPreferences.getInt(CURRENT_STREAK_KEY, 0);
        int skillPoints = sharedPreferences.getInt(SKILL_POINTS_KEY, 0);

        tvStreakCounter.setText("🔥 " + currentStreak);
        tvSkillPoints.setText("Points: " + skillPoints);

        updateBadge(currentStreak);
    }

    private void updateBadge(int streak) {
        if (streak >= 15) {
            tvBadgeProgression.setText("Consistency King 👑");
        } else if (streak >= 7) {
            tvBadgeProgression.setText("Skill Master 🌟");
        } else if (streak >= 3) {
            tvBadgeProgression.setText("Learner 🎯");
        } else {
            tvBadgeProgression.setText("Learner 🎯");
        }
    }
}
