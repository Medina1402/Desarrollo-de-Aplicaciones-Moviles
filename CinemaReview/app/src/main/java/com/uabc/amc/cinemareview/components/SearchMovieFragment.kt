package com.uabc.amc.cinemareview.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uabc.amc.cinemareview.R

class SearchMovieFragment(private val movies: List<MovieImageFragment>): RecyclerView.Adapter<SearchMovieFragment.SearchMovieFragmentHolder>() {
    inner class SearchMovieFragmentHolder(item: View) : RecyclerView.ViewHolder(item)
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieFragmentHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_history_item_movie, parent, false)
        return SearchMovieFragmentHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMovieFragmentHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}