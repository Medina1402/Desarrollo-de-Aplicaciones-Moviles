package com.uabc.amc.workout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {
    companion object {
        var EXTRA_WORKOUT_ID: String = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as WorkoutDetailFragment
        val workoutId = intent.extras?.get(EXTRA_WORKOUT_ID) as Int
        frag.setWorkout(workoutId.toLong())
    }
}