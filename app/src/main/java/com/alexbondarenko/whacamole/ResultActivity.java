package com.alexbondarenko.whacamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView score, record, congrats;
    Button buttonPlayAgain, buttonMenu;
    LinearLayout linearLayout;
    Intent intent;
    int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        score = findViewById(R.id.ar_score);
        record = findViewById(R.id.ar_record);
        congrats = findViewById(R.id.ar_recordCongrats);
        buttonPlayAgain = findViewById(R.id.ar_btnPlayAgain);
        buttonMenu = findViewById(R.id.ar_btnMainMenu);
        linearLayout = findViewById(R.id.ar_linearLayout);
        setAnimations();

        congrats.setVisibility(View.INVISIBLE);

        setStats();

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setStats() {
        SharedPreferences settings = getSharedPreferences("Record", Context.MODE_PRIVATE);
        int currRecord = settings.getInt("Record", 0);

        intent = getIntent();
        userScore = intent.getIntExtra("Score", 0);
        score.setText(Integer.toString(userScore));

        if (userScore > currRecord) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("Record", userScore);
            editor.apply();
            congrats.setVisibility(View.VISIBLE);
            record.setText(Integer.toString(userScore));
        } else
            record.setText(Integer.toString(currRecord));

    }

    private void setAnimations() {
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


    }

}