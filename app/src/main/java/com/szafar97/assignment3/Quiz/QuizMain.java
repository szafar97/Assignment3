package com.szafar97.assignment3.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.szafar97.assignment3.MainActivity;
import com.szafar97.assignment3.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class QuizMain extends MainActivity {

    ArrayList<List<String>> arrayList; //It will contain MCQs along with their options
    int current_MCQ; //Store the number of current ongoing MCQ
    final int maxMCQNo = 30; //Highest MCQ number in XML file
    final int totalMCQs = 10; //Total MCQs in a single quiz
    final int mcqTime = 15; //Time to complete a single MCQ
    List<Integer> randomList; //Will contain random numbers for randomization of options
    int score; //It will count the score (number of correctly attempted MCQs)
    TextView question_No; //To display quesion number
    TextView question; //To display question
    TextView timerText; //To display timer of question
    CountDownTimer timer; //MCQ Timer
    Button nextButton; //Next MCQ button
    RadioGroup radioGroup; //Contains Options
    List<RadioButton> options; //Contains the list of radio buttons
    String correctOption; //Contains the correct answer of current MCQ
    int toggle = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quiz_main);

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_quiz_main, null, false);
        toolbar.setTitle("Quiz Section");
        navigationView.getMenu().getItem(1).setEnabled(false);
        drawerLayout.addView(contentView, 0);

        question_No = findViewById(R.id.question_no);
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radioGroup);
        nextButton = findViewById(R.id.nextButton);
        timerText = findViewById(R.id.timer);

        int count = radioGroup.getChildCount();
        options = new ArrayList<RadioButton>();
        for (int i = 0; i < count; i++)
        {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton)
                options.add((RadioButton)o);
        }

        randomList = new ArrayList<Integer>();

        for (int i = 1; i <= 4; i++)
            randomList.add(i);

        arrayList = new ArrayList<List<String>>();
        String []data;
        int mcqID;

        for (int i = 0; i <= 30; i++)
        {
            mcqID = this.getResources().getIdentifier("mcq"+i, "array", this.getPackageName());
            data = getResources().getStringArray(mcqID);
            arrayList.add(asList(data));
        }

        Collections.shuffle(arrayList); //It will shuffle the MCQs randomly

        //MCQ Timer
        timer = new CountDownTimer(mcqTime * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(mcqTime - counter) + "s left");
                counter++;
            }

            public void onFinish() {
                nextMCQ(nextButton);
                timerText.setText("Time Up");
                counter = 0;
            }
        };

        showNextMCQ();
    }

    private void showNextMCQ()
    {
        if (current_MCQ < totalMCQs)
        {
            int j = 0;
            List<String> MCQ = arrayList.get(current_MCQ);
            question_No.setText(String.valueOf(current_MCQ + 1));
            question.setText(MCQ.get(0));
            correctOption = MCQ.get(1);
            Collections.shuffle(randomList);

            for (Iterator<RadioButton> i = options.iterator(); i.hasNext(); ) {
                RadioButton rb = i.next();
                rb.setBackgroundColor(Color.TRANSPARENT);
                rb.setEnabled(true);
                rb.setText(MCQ.get(randomList.get(j)));
                j++;
            }

            current_MCQ++;
            timer.start();
        }
        else
        {
            Intent intent = new Intent(this, QuizResult.class);
            intent.putExtra("score", score);
            intent.putExtra("total", totalMCQs);
            startActivity(intent);

            finish();
        }
    }

    public void nextMCQ(View view)
    {
        timer.cancel();
        counter = 0;

        if (1 - toggle  == 1)
        {
            int id = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);
            RadioButton rb1 = null;

            if (rb.getText().toString().equals(correctOption))
            {
                rb.setBackgroundColor(Color.GREEN);
                score++;
            }
            else
            {
                rb.setBackgroundColor(Color.RED);

                for (Iterator<RadioButton> i = options.iterator(); i.hasNext();)
                {
                    rb1 = i.next();
                    if(rb1.getText().toString().equals(correctOption))
                    {
                        rb1.setBackgroundColor(Color.GREEN);
                        break;
                    }
                }
            }

            for (Iterator<RadioButton> i = options.iterator(); i.hasNext();)
            {
                RadioButton rb2 = i.next();
                rb2.setEnabled(false);
            }

            toggle = 1 - toggle;

            if (current_MCQ == totalMCQs)
                nextButton.setText("SUBMIT");
            else
                nextButton.setText("NEXT");
        }
        else
        {
            toggle = 1 - toggle;

            if (current_MCQ != totalMCQs)
                nextButton.setText("VIEW ANSWER");
            showNextMCQ();
        }
    }
}