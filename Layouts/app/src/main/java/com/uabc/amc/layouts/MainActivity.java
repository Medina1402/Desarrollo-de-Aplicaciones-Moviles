package com.uabc.amc.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeContentViewLayout(String type) {
        Intent intent = new Intent(this, ExampleLayout.class);
        intent.putExtra(ExampleLayout.ACTIVITY_ACTIVE, type);
        startActivity(intent);
    }

    public void changeLayoutToLinear(View view) {
        changeContentViewLayout("linear");
    }

    public void changeLayoutToGrid(View view) {
        changeContentViewLayout("grid");
    }

    public void changeLayoutToFrame(View view) {
        changeContentViewLayout("frame");
    }

    public void changeLayoutToExercise1(View view) {
        changeContentViewLayout("exercise_1");
    }

    public void changeLayoutToExercise2(View view) {
        changeContentViewLayout("exercise_2");
    }
}