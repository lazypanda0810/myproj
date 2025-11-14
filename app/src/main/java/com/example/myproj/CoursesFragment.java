package com.example.myproj;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    private RecyclerView rvCourses;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> courseList;

    private EditText etSearchCourses;
    private Button btnFilterAll, btnFilterBeginner, btnFilterIntermediate, btnFilterAdvanced;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        rvCourses = view.findViewById(R.id.rv_courses);
        etSearchCourses = view.findViewById(R.id.et_search_courses);
        btnFilterAll = view.findViewById(R.id.btn_filter_all);
        btnFilterBeginner = view.findViewById(R.id.btn_filter_beginner);
        btnFilterIntermediate = view.findViewById(R.id.btn_filter_intermediate);
        btnFilterAdvanced = view.findViewById(R.id.btn_filter_advanced);

        courseList = MockData.getCourses();
        courseAdapter = new CourseAdapter(courseList);

        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCourses.setAdapter(courseAdapter);

        setupSearch();
        setupFilterButtons();

        return view;
    }

    private void setupSearch() {
        etSearchCourses.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void setupFilterButtons() {
        btnFilterAll.setOnClickListener(v -> filterByDifficulty("All"));
        btnFilterBeginner.setOnClickListener(v -> filterByDifficulty("Beginner"));
        btnFilterIntermediate.setOnClickListener(v -> filterByDifficulty("Intermediate"));
        btnFilterAdvanced.setOnClickListener(v -> filterByDifficulty("Advanced"));
    }

    private void filter(String text) {
        ArrayList<Course> filteredList = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(course);
            }
        }
        courseAdapter.filterList(filteredList);
    }

    private void filterByDifficulty(String difficulty) {
        if (difficulty.equals("All")) {
            courseAdapter.filterList(courseList);
            return;
        }

        ArrayList<Course> filteredList = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getDifficulty().equalsIgnoreCase(difficulty)) {
                filteredList.add(course);
            }
        }
        courseAdapter.filterList(filteredList);
    }
}
