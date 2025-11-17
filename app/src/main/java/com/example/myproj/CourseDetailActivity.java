package com.example.myproj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashSet;
import java.util.Set;

public class CourseDetailActivity extends AppCompatActivity implements LessonAdapter.OnLessonCompletedListener {

    public static final String EXTRA_COURSE = "course";

    private TextView tvCourseTitleDetail;
    private TextView tvCourseOverviewDetail;
    private Button btnTakeQuiz;
    private RecyclerView rvLessons;
    private ProgressBar pbCourseProgress;
    private Course course;
    private LessonAdapter lessonAdapter;
    private SharedPreferences prefs;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        tvCourseTitleDetail = findViewById(R.id.tv_course_title_detail);
        tvCourseOverviewDetail = findViewById(R.id.tv_course_overview_detail);
        btnTakeQuiz = findViewById(R.id.btn_take_quiz);
        rvLessons = findViewById(R.id.rv_lessons);
        pbCourseProgress = findViewById(R.id.pb_course_progress);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        course = (Course) getIntent().getSerializableExtra(EXTRA_COURSE);

        if (course != null) {
            tvCourseTitleDetail.setText(course.getTitle());
            tvCourseOverviewDetail.setText(course.getOverview());
            trackCourseViewed();
            loadCompletedLessons();
            setupRecyclerView();
            updateProgress();
        }

        btnTakeQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(CourseDetailActivity.this, QuizActivity.class);
            intent.putExtra(QuizActivity.EXTRA_COURSE_ID, Integer.parseInt(course.getId()));
            intent.putExtra(QuizActivity.EXTRA_COURSE_NAME, course.getTitle());
            intent.putExtra(QuizActivity.EXTRA_COMPLETION_PERCENTAGE, pbCourseProgress.getProgress());
            startActivity(intent);
        });
    }

    private void trackCourseViewed() {
        Set<String> coursesViewed = prefs.getStringSet("courses_viewed", new HashSet<>());
        Set<String> updatedSet = new HashSet<>(coursesViewed);
        updatedSet.add(String.valueOf(course.getId()));
        prefs.edit().putStringSet("courses_viewed", updatedSet).apply();
    }

    private void setupRecyclerView() {
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        lessonAdapter = new LessonAdapter(this, course.getLessons(), this);
        rvLessons.setAdapter(lessonAdapter);
    }

    private void loadCompletedLessons() {
        if (course.getLessons() == null) return;
        for (Lesson lesson : course.getLessons()) {
            boolean isCompleted = prefs.getBoolean("lesson_" + lesson.getId(), false);
            lesson.setCompleted(isCompleted);
        }
    }

    @Override
    public void onLessonCompleted(int lessonId, boolean isCompleted) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("lesson_" + lessonId, isCompleted);
        editor.apply();

        if (course.getLessons() == null) return;
        for (Lesson lesson : course.getLessons()) {
            if (Integer.parseInt(lesson.getId()) == lessonId) {
                lesson.setCompleted(isCompleted);
                break;
            }
        }
        lessonAdapter.notifyDataSetChanged();
        updateProgress();
    }

    private void updateProgress() {
        if (course.getLessons() == null || course.getLessons().isEmpty()) {
            pbCourseProgress.setProgress(0);
            btnTakeQuiz.setVisibility(View.GONE);
            return;
        }
        int completedLessons = 0;
        for (Lesson lesson : course.getLessons()) {
            if (lesson.isCompleted()) {
                completedLessons++;
            }
        }
        int progress = (int) (((float) completedLessons / course.getLessons().size()) * 100);
        pbCourseProgress.setProgress(progress);

        if (progress > 80) {
            btnTakeQuiz.setVisibility(View.VISIBLE);
            if (progress == 100) {
                Snackbar.make(findViewById(R.id.course_detail_container), "Congratulations! You have completed the course.", Snackbar.LENGTH_LONG)
                        .setAction("Take Test", v -> btnTakeQuiz.performClick())
                        .show();
            }
        } else {
            btnTakeQuiz.setVisibility(View.GONE);
        }
    }
}
