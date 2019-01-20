package com.example.android.stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        //string for adding the word "time" before the chronometer

        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime()-chronometer.getBase())>= 600000)  {
                    //time resets when it reaches 600000 milliseconds
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void startChronometer (View v){
        if (!running){
            chronometer.setBase(SystemClock.elapsedRealtime()- pauseOffSet);
            //makes sure that time the clock starts at zero
            chronometer.start();
            running=true;
        }

    }

    public void pauseChronometer (View v){
        if (running){
            chronometer.stop();
            pauseOffSet = SystemClock.elapsedRealtime()- chronometer.getBase();
            //makes sure that time that passes is not counted when the timer resumes
            running=false;

        }

    }

    public void resetChronometer (View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffSet =0;

    }

}
