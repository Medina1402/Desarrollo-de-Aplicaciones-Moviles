package com.uabc.amc.workout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction

class TempActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        if(savedInstanceState == null) {
            val stopwatch = StopwatchFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.stopwatch_container, stopwatch)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }

    }
}