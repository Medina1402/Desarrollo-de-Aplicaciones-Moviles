package com.uabc.amc.cinemareview.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uabc.amc.cinemareview.R
import kotlinx.android.synthetic.main.item_movie_horizontal_scroll.view.*

class MovieFragmentHorizontalView(private val scrollHorizonList: List<MovieFragmentHorizontal>, private val context: Context) : RecyclerView.Adapter<MovieFragmentHorizontalView.MovieFragmentHorizontalViewHolder>() {
    inner class MovieFragmentHorizontalViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFragmentHorizontalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_horizontal_scroll, parent, false)
        return MovieFragmentHorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieFragmentHorizontalViewHolder, position: Int) {
        val movie = scrollHorizonList[position]
        holder.itemView.movie_title_scroll_list.text = movie.title

        val recycler = holder.itemView.linear_layout_horizontal_scroll_image
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = MovieImageFragmentView(movie.movies)
    }

    override fun getItemCount(): Int {
        return scrollHorizonList.size
    }
}

class MovieFragmentHorizontal(val title: String, val movies: List<MovieImageFragment>)