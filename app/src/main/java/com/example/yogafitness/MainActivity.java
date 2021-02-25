package com.example.yogafitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {


    Button btnExercise,btnSetting,btnCalendar;
    ImageView btnTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnExercise = (FButton) findViewById(R.id.btnExercise);
        btnSetting = (FButton) findViewById(R.id.btnSetting);
        btnTraining = (ImageView) findViewById(R.id.btnTraining) ;
        btnCalendar = (Button)findViewById(R.id.btnCalendar);


        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, Daily_Training.class);
                startActivity(intent);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, SettingPage.class);
                startActivity(intent);
            }
        });

        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, ListExercises.class);
                startActivity(intent);
            }
        });

    }
}