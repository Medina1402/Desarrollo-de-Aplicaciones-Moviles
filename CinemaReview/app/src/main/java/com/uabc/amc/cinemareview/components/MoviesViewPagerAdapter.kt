package com.uabc.amc.cinemareview.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uabc.amc.cinemareview.R
import kotlinx.android.synthetic.main.item_view_pager_movie_view.view.*

class MoviesViewPagerAdapter(private val movies: List<MovieViewPager>): RecyclerView.Adapter<MoviesViewPagerAdapter.MovieViewPagerViewHolder>() {
    inner class MovieViewPagerViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager_movie_view, parent, false)
        return MovieViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewPagerViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.image_view_item_pager_movie.setImageResource(movie.image)
        holder.itemView.title_view_item_pager_movie.text = movie.title
        holder.itemView.duration_view_item_pager_movie.text = movie.duration
        holder.itemView.category_view_item_pager_movie.text = movie.category
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MovieViewPager(val title: String, val duration: String, val category: String, val image: Int)