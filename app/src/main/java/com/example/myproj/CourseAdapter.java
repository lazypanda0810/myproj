package com.example.myproj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.tvCourseTitle.setText(course.getTitle());
        holder.tvCourseOverview.setText(course.getOverview());
        holder.chipDifficulty.setText(course.getDifficulty());
        holder.tvDuration.setText(course.getDuration());

        // Calculate and display progress
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("user_progress", Context.MODE_PRIVATE);
        int completedLessons = 0;
        int progress = 0;
        if (course.getLessons() != null && !course.getLessons().isEmpty()) {
            for (Lesson lesson : course.getLessons()) {
                if (sharedPreferences.getBoolean("lesson_" + lesson.getId(), false)) {
                    completedLessons++;
                }
            }
            progress = (int) (((float) completedLessons / course.getLessons().size()) * 100);
        }
        holder.progressBar.setProgress(progress);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, CourseDetailActivity.class);
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE, course);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void filterList(ArrayList<Course> filteredList) {
        courses = filteredList;
        notifyDataSetChanged();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseTitle;
        TextView tvCourseOverview;
        Chip chipDifficulty;
        TextView tvDuration;
        ProgressBar progressBar;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseTitle = itemView.findViewById(R.id.tv_course_title);
            tvCourseOverview = itemView.findViewById(R.id.tv_course_overview);
            chipDifficulty = itemView.findViewById(R.id.chip_difficulty);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            progressBar = itemView.findViewById(R.id.progress_bar_course);
        }
    }
}
