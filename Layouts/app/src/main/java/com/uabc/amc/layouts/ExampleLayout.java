package com.uabc.amc.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class ExampleLayout extends AppCompatActivity {
    public static final String ACTIVITY_ACTIVE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(Objects.equals(intent.getStringExtra(ACTIVITY_ACTIVE), "linear")) {
            setContentView(R.layout.linear_layout);
            setTitle("Linear Layout");
        } else if(Objects.equals(intent.getStringExtra(ACTIVITY_ACTIVE), "grid")) {
            setContentView(R.layout.grid_layout);
            setTitle("Grid Layout");
        } else if(Objects.equals(intent.getStringExtra(ACTIVITY_ACTIVE), "frame")) {
            setContentView(R.layout.frame_layout);
            setTitle("Frame Layout");
        } else if(Objects.equals(intent.getStringExtra(ACTIVITY_ACTIVE), "exercise_1")) {
            setContentView(R.layout.exercise_1);
            setTitle("Exercise 1");
        } else {
            setContentView(R.layout.exercise_2);
            setTitle("Exercise 2");
        }
    }
}