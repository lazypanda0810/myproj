package com.example.myproj;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class CoursesActivity extends AppCompatActivity {

    private RecyclerView rvCourses;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> allCourses;
    private EditText etSearchCourses;
    private Button btnFilterAll, btnFilterBeginner, btnFilterIntermediate, btnFilterAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        rvCourses = findViewById(R.id.rv_courses);
        etSearchCourses = findViewById(R.id.et_search_courses);
        btnFilterAll = findViewById(R.id.btn_filter_all);
        btnFilterBeginner = findViewById(R.id.btn_filter_beginner);
        btnFilterIntermediate = findViewById(R.id.btn_filter_intermediate);
        btnFilterAdvanced = findViewById(R.id.btn_filter_advanced);

        allCourses = MockData.getCourses();
        courseAdapter = new CourseAdapter(allCourses);

        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseAdapter);

        setupSearch();
        setupFilterButtons();
    }

    private void setupSearch() {
        etSearchCourses.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupFilterButtons() {
        btnFilterAll.setOnClickListener(v -> filter(""));
        btnFilterBeginner.setOnClickListener(v -> filter("Beginner"));
        btnFilterIntermediate.setOnClickListener(v -> filter("Intermediate"));
        btnFilterAdvanced.setOnClickListener(v -> filter("Advanced"));
    }

    private void filter(String text) {
        ArrayList<Course> filteredList = new ArrayList<>();
        String filterText = text.toLowerCase(Locale.getDefault());

        for (Course course : allCourses) {
            if (course.getTitle().toLowerCase(Locale.getDefault()).contains(filterText) ||
                course.getDifficulty().toLowerCase(Locale.getDefault()).contains(filterText)) {
                filteredList.add(course);
            }
        }
        courseAdapter.filterList(filteredList);
    }
}
