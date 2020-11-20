package com.uabc.amc.workout

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.uabc.amc.workout.Workout.Companion.Workouts

class WorkoutListFragment: ListFragment() {
    companion object {
        interface Listener { fun itemClicked(id: Long) }
    }
    private lateinit var listener: Listener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val names: Array<String> = Workouts.map { T -> T.getName() }.toTypedArray()
        this.listAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, names)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        listener.itemClicked(id)
    }
}