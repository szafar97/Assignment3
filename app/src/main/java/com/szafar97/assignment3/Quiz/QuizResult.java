package com.szafar97.assignment3.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.szafar97.assignment3.MainActivity;
import com.szafar97.assignment3.R;

public class QuizResult extends MainActivity {

    TextView text;
    int score;
    int totalMCQs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quiz_result);

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_quiz_result, null, false);
        toolbar.setTitle("Quiz Section");
        navigationView.getMenu().getItem(1).setEnabled(false);
        drawerLayout.addView(contentView, 0);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        totalMCQs = intent.getIntExtra("total", 0);

        text = findViewById(R.id.score);
        text.setText(String.valueOf(score));

        String remarks = "";

        if (score > 0)
            remarks = "You have answered " + score + " MCQs correctly out of " + totalMCQs + " MCQs ";
        else
            remarks = "You haven't answer any MCQ correctly out of " + totalMCQs + " MCQs.";

        text = findViewById(R.id.remarks);
        text.setText(remarks);
    }

    public void Start_Again(View view)
    {
        finish();
        return;
    }
}