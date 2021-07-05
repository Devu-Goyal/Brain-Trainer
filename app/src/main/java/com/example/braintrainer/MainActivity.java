package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton,AnswerButton3,AnswerButton2,AnswerButton1,AnswerButton0,PlayAgainButton;
    TextView ScoreTextView,QuestionTextView,TimerTextView,Correctness;
    ConstraintLayout GameLayout;
    ArrayList<Integer> options = new ArrayList<Integer>();
    int score=0,TotalQuestions=0,position,flag=0;

    public void ChooseAnswer(View view){
        if(flag==1) {
            int sec = 1000;
            if (Integer.parseInt(view.getTag().toString()) == position) {
                Log.i("Correct", "Noice");
                Toast toast = Toast.makeText(this, "Correct!!", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.LEFT | Gravity.TOP, 400, 870);
                score++;
            } else {
                Log.i("Tag:", "Sorry");
                Toast toast = Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.LEFT | Gravity.TOP, 400, 870);
            }
            TotalQuestions++;
            ScoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(TotalQuestions));
            newQuestion();
        }
    }

    public void PlayAgain(View view){
        flag=1;
        score=0;
        TotalQuestions=0;
        ScoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(TotalQuestions));
        newQuestion();
        TimerTextView.setText("30s");
        Timer();
        Correctness.setVisibility(View.INVISIBLE);
        PlayAgainButton.setVisibility(View.INVISIBLE);
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        GameLayout.setVisibility(View.VISIBLE);
        newQuestion();
        Timer();
        flag=1;
    }

    public void Timer(){
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                int k = (int) (l/1000);
                TimerTextView.setText(String.valueOf(k)+"s");
            }

            @Override
            public void onFinish() {
                Correctness.setVisibility(View.VISIBLE);
                PlayAgainButton.setVisibility(View.VISIBLE);
                flag=0;
                Correctness.setText("Time's up !!");
            }
        }.start();
    }

    public void newQuestion(){
        options.clear();
        Random rand = new Random();
        int min,a=0,b=0,i,j,f=0,num,num1,num2;
        num1=rand.nextInt(50);
        num2=rand.nextInt(50);

        QuestionTextView.setText(Integer.toString(num1)+" + "+Integer.toString(num2));

        if(num1+num2<12)
            min=0;
        else
            min=num1+num2-12;

        position = rand.nextInt(4);

        for(i=0;i<4;i++){
            if(i==position){
                options.add(num1+num2);
            }
            else {
                if(f==0) {
                    num = rand.nextInt(30);
                    while (num + min == num1 + num2) {
                        num = rand.nextInt(30);
                    }
                    a = num + min;
                    options.add(a);
                    f++;
                }
                else if(f==1) {
                    num = rand.nextInt(30);
                    while (num + min == a) {
                        num = rand.nextInt(30);
                    }
                    b = num + min;
                    options.add(b);
                    f++;
                }
                else {
                    num = rand.nextInt(30);
                    while (num + min == a || num + min == b) {
                        num = rand.nextInt(30);
                    }
                    options.add(num + min);
                }
            }
        }
        AnswerButton0.setText(Integer.toString(options.get(0)));
        AnswerButton1.setText(Integer.toString(options.get(1)));
        AnswerButton2.setText(Integer.toString(options.get(2)));
        AnswerButton3.setText(Integer.toString(options.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.GoButton);
        GameLayout = findViewById(R.id.GameLayout);
        ScoreTextView = findViewById(R.id.ScoreTextView);
        QuestionTextView = findViewById(R.id.QuestionTextView);
        TimerTextView = findViewById(R.id.TimerTextView);
        Correctness = findViewById(R.id.Correctness);
        Correctness.setVisibility(View.INVISIBLE);
        AnswerButton3 = findViewById(R.id.AnswerButton3);
        AnswerButton0 = findViewById(R.id.AnswerButton0);
        AnswerButton1 = findViewById(R.id.AnswerButton1);
        AnswerButton2 = findViewById(R.id.AnswerButton2);
        PlayAgainButton = findViewById(R.id.PlayAgainButton);
        PlayAgainButton.setVisibility(View.INVISIBLE);

        goButton.setVisibility(View.VISIBLE);
        GameLayout.setVisibility(View.INVISIBLE);

    }
}