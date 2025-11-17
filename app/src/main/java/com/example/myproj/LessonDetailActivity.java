package com.example.myproj;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LessonDetailActivity extends AppCompatActivity {

    public static final String EXTRA_LESSON = "lesson";

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        TextView tvTitle = findViewById(R.id.tv_lesson_title);
        TextView tvPointers = findViewById(R.id.tv_pointers_content);
        TextView tvTheory = findViewById(R.id.tv_theory_content);
        TextView tvCode = findViewById(R.id.tv_code_content);

        Lesson lesson = (Lesson) getIntent().getSerializableExtra(EXTRA_LESSON);

        if (lesson != null) {
            // Defensively set text only if views and data are not null
            if (tvTitle != null && lesson.getTitle() != null) {
                tvTitle.setText(lesson.getTitle());
            }
            if (tvPointers != null && lesson.getPointers() != null) {
                tvPointers.setText(lesson.getPointers());
            }
            if (tvTheory != null && lesson.getTheory() != null) {
                tvTheory.setText(lesson.getTheory());
            }
            if (tvCode != null && lesson.getCodeExamples() != null) {
                tvCode.setText(lesson.getCodeExamples());
            }
        }
    }
}
