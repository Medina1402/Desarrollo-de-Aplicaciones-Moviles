package com.uabc.amc.cinemareview.components

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.pages.MovieDescriptionItem
import kotlinx.android.synthetic.main.fragment_history_item_movie.view.*

class HistoryFragmentMovie(private val movies: List<MovieImageFragment>, private val context: Context): RecyclerView.Adapter<HistoryFragmentMovie.HistoryFragmentMovieHolder>() {
    inner class HistoryFragmentMovieHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryFragmentMovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_history_item_movie, parent, false)
        return HistoryFragmentMovieHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryFragmentMovieHolder, position: Int) {
        val movie = movies[position]

        holder.itemView.history_movie_name.text = movie.name
        holder.itemView.history_movie_categories.text = movie.categories
        holder.itemView.history_movie_stars.text = movie.stars
        holder.itemView.history_movie_duration.text = movie.duration
        holder.itemView.history_movie_director.text = movie.director

        Glide.with(context).load(movie.image).into(holder.itemView.history_movie_cover)
        Glide.with(context).load(movie.bannerImage).into(holder.itemView.history_movie_banner)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDescriptionItem::class.java)
            intent
                .putExtra("MOVIE_ID", movie.id)
                .putExtra("MOVIE_ID_DOCUMENT", movie.idDocument)
                .putExtra("MOVIE_NAME", movie.name)
                .putExtra("MOVIE_COVER", movie.image)
                .putExtra("MOVIE_BANNER", movie.bannerImage)
                .putExtra("MOVIE_DURATION", movie.duration)
                .putExtra("MOVIE_CATEGORIES", movie.categories)
                .putExtra("MOVIE_SINOPSIS", movie.sinopsis)
                .putExtra("MOVIE_STARS", movie.stars)
                .putExtra("MOVIE_DIRECTOR", movie.director)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}