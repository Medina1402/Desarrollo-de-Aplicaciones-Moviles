package com.uabc.amc.cinemareview.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uabc.amc.cinemareview.R
import kotlinx.android.synthetic.main.item_movie_image_fragment_view.view.*

class MovieImageFragmentView(private val movies: List<MovieImageFragment>) : RecyclerView.Adapter<MovieImageFragmentView.MovieImageFragmentViewHolder>() {
    inner class MovieImageFragmentViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImageFragmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_image_fragment_view, parent, false)
        return MovieImageFragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieImageFragmentViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.image_view_movie_poster.setImageResource(movie.image)
        holder.itemView.setOnClickListener {
            println("Evento: ${movie.id}")
            // todo Crear el intent aqui
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MovieImageFragment(val id: Int, val image: Int)