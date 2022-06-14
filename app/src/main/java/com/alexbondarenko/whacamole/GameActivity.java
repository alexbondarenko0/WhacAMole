package com.alexbondarenko.whacamole;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ToggleButton tg1, tg2, tg3, tg4, tg5, tg6, tg7, tg8, tg9;
    TextView tvScore, tvTime;
    ArrayList<ToggleButton> buttons;
    Random rand;
    Handler timeHandler;
    Runnable updater, timer;
    int score;
    double time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        tvScore = findViewById(R.id.ag_scoreCount);
        tvTime = findViewById(R.id.ag_timeCount);

        buttons = new ArrayList<>();
        rand = new Random();
        initButtons();

        addOnClicks();

        startMoles();
        startTimer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timeHandler.removeCallbacks(updater);
        timeHandler.removeCallbacks(timer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startMoles();
        startTimer();
    }

    private void startMoles() {
        timeHandler = new Handler();
        updater = new Runnable() {
            @Override
            public void run() {
                randomButtonChecked();
                timeHandler.postDelayed(updater, 500);
            }
        };
        timeHandler.post(updater);
    }

    private void startTimer() {
        score = 0;
        tvScore.setText(Integer.toString(score));
        time = 30;
        timer = new Runnable() {
            @Override
            public void run() {
                if (time > 0) {
                    String timeStr = String.format("%.1f", time);
                    tvTime.setText(timeStr);
                    time -= 0.1;

                    timeHandler.postDelayed(timer, 100);
                } else {
                    Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                    intent.putExtra("Score", score);
                    startActivity(intent);
                }
            }
        };
        timeHandler.post(timer);
    }

    private void randomButtonChecked() {
        for (ToggleButton b : buttons) {
            b.setChecked(false);
        }
        int x = rand.nextInt(buttons.size());
        buttons.get(x).setChecked(true);
    }

    private void addOnClicks() {
        for (ToggleButton b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (b.isChecked()) {
                        b.setChecked(false);
                    } else {
                        score++;
                        tvScore.setText(Integer.toString(score));
                        b.setChecked(false);
                        //randomButtonChecked();
                    }

                }
            });
        }
    }

    private void initButtons() {
        tg1 = findViewById(R.id.tg1);
        tg2 = findViewById(R.id.tg2);
        tg3 = findViewById(R.id.tg3);
        tg4 = findViewById(R.id.tg4);
        tg5 = findViewById(R.id.tg5);
        tg6 = findViewById(R.id.tg6);
        tg7 = findViewById(R.id.tg7);
        tg8 = findViewById(R.id.tg8);
        tg9 = findViewById(R.id.tg9);

        buttons.add(tg1);
        buttons.add(tg2);
        buttons.add(tg3);
        buttons.add(tg4);
        buttons.add(tg5);
        buttons.add(tg6);
        buttons.add(tg7);
        buttons.add(tg8);
        buttons.add(tg9);
    }



}