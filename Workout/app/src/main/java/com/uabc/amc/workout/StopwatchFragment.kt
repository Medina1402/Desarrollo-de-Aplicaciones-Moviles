package com.uabc.amc.workout

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*

class StopwatchFragment : Fragment(), View.OnClickListener {
    private var seconds = 0;
    private var running: Boolean = false
    private var wasRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        runTimer(layout)

        layout.findViewById<Button>(R.id.start_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.stop_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.reset_button).setOnClickListener(this)

        return layout
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    private fun runTimer(view: View?) {
        val textView = view?.findViewById<TextView>(R.id.time_view)
        val handler = Handler()

        handler.post(object: Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                textView?.text = String.format(
                    Locale.getDefault(),
                    "%2d:%02d:%02d",
                    hours, minutes, secs
                )

                if(running) seconds++
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
        }
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickStart() {
        running = true
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if(wasRunning) running = true
    }
}