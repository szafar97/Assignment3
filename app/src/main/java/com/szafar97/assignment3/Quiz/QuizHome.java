package com.szafar97.assignment3.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.szafar97.assignment3.MainActivity;
import com.szafar97.assignment3.R;

public class QuizHome extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quiz_home);

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_quiz_home, null, false);
        toolbar.setTitle("Quiz Section");
        navigationView.getMenu().getItem(1).setEnabled(false);
        drawerLayout.addView(contentView, 0);
    }

    public void StartQuiz(View view)
    {
        Intent intent = new Intent(this, QuizMain.class);
        startActivity(intent);
    }
}