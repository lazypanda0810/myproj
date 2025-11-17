package com.example.myproj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private final List<Lesson> lessons;
    private final Context context;
    private final OnLessonCompletedListener listener;

    public interface OnLessonCompletedListener {
        void onLessonCompleted(int lessonId, boolean isCompleted);
    }

    public LessonAdapter(Context context, List<Lesson> lessons, OnLessonCompletedListener listener) {
        this.context = context;
        this.lessons = lessons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_item, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.tvLessonTitle.setText(lesson.getTitle());
        holder.tvLessonDuration.setText(lesson.getDuration());

        if (lesson.isCompleted()) {
            holder.btnMarkAsComplete.setVisibility(View.GONE);
            holder.tvLessonCompletedStatus.setVisibility(View.VISIBLE);
        } else {
            holder.btnMarkAsComplete.setVisibility(View.VISIBLE);
            holder.tvLessonCompletedStatus.setVisibility(View.GONE);
        }

        holder.btnMarkAsComplete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onLessonCompleted(Integer.parseInt(lesson.getId()), true);
            }
        });

        holder.btnWatch.setOnClickListener(v -> {
            Intent intent = new Intent(context, LessonDetailActivity.class);
            intent.putExtra(LessonDetailActivity.EXTRA_LESSON, lesson);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonTitle;
        TextView tvLessonDuration;
        Button btnWatch;
        Button btnMarkAsComplete;
        TextView tvLessonCompletedStatus;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLessonTitle = itemView.findViewById(R.id.tv_lesson_title);
            tvLessonDuration = itemView.findViewById(R.id.tv_lesson_duration);
            btnWatch = itemView.findViewById(R.id.btn_watch);
            btnMarkAsComplete = itemView.findViewById(R.id.btn_mark_as_complete);
            tvLessonCompletedStatus = itemView.findViewById(R.id.tv_lesson_completed_status);
        }
    }
}
