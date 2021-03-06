package com.uabc.amc.workout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.uabc.amc.workout.Workout.Companion.Workouts


class WorkoutDetailFragment : Fragment() {
    private var workoutId: Long? = null

    fun setWorkout(id: Long) {
        this.workoutId = id
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            val stopwatch = StopwatchFragment()
            val ft = childFragmentManager.beginTransaction()
            ft.add(R.id.stopwatch_container, stopwatch)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()

        } else {
            workoutId = savedInstanceState.getLong("workoutId")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        workoutId?.let { outState.putLong("workoutId", it) }
    }

    override fun onStart() {
        super.onStart()

        if(view != null) {
            val workout = Workouts[workoutId?.toInt()!!]

            val title = view!!.findViewById<TextView>(R.id.textTitle)
            title.text = workout.getName()

            val description = view!!.findViewById<TextView>(R.id.textDescription)
            description.text = workout.getDescription()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }
}