package com.alexbondarenko.whacamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView record;
    Button buttonStart, buttonResetRecord;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        record = findViewById(R.id.ma_record);
        buttonStart = findViewById(R.id.ma_btnStart);
        buttonResetRecord = findViewById(R.id.ma_btnReset);
        settings = getSharedPreferences("Record", Context.MODE_PRIVATE);

        setCurrentRecord();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        buttonResetRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("Record", 0);
                editor.apply();
                setCurrentRecord();
            }
        });


    }

    private void setCurrentRecord() {
        record.setText(Integer.toString(settings.getInt("Record", 0)));
    }
}