package com.example.myproj;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FeedbackFragment extends Fragment {

    private static final String PREFS_NAME = "SkillUpPrefs";
    private static final String FEEDBACK_KEY = "feedback";

    private RatingBar ratingBar;
    private EditText etFeedbackComment;
    private Button btnSubmitFeedback;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        ratingBar = view.findViewById(R.id.rating_bar);
        etFeedbackComment = view.findViewById(R.id.et_feedback_comment);
        btnSubmitFeedback = view.findViewById(R.id.btn_submit_feedback);
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();

        btnSubmitFeedback.setOnClickListener(v -> {
            String comment = etFeedbackComment.getText().toString();
            float rating = ratingBar.getRating();

            if (rating == 0) {
                Toast.makeText(getActivity(), "Please provide a rating.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (comment.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter a comment.", Toast.LENGTH_SHORT).show();
                return;
            }

            saveFeedback(new Feedback(rating, comment));

            Toast.makeText(getActivity(), "Thank You for your feedback!", Toast.LENGTH_SHORT).show();

            // Clear the form
            ratingBar.setRating(0);
            etFeedbackComment.setText("");
        });

        return view;
    }

    private void saveFeedback(Feedback feedback) {
        String json = sharedPreferences.getString(FEEDBACK_KEY, "[]");
        Type type = new TypeToken<ArrayList<Feedback>>() {}.getType();
        List<Feedback> feedbackList = gson.fromJson(json, type);
        feedbackList.add(feedback);
        String updatedJson = gson.toJson(feedbackList);
        sharedPreferences.edit().putString(FEEDBACK_KEY, updatedJson).apply();
    }
}
