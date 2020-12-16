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
import kotlinx.android.synthetic.main.item_movie_image_fragment_view.view.*

class MovieImageFragmentView(private val movies: List<MovieImageFragment>, private val context: Context) : RecyclerView.Adapter<MovieImageFragmentView.MovieImageFragmentViewHolder>() {

    inner class MovieImageFragmentViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImageFragmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_image_fragment_view, parent, false)
        return MovieImageFragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieImageFragmentViewHolder, position: Int) {
        val movie = movies[position]

        Glide.with(context).load(movie.image).into(holder.itemView.image_view_movie_poster)

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

class MovieImageFragment(
    val id: String,
    val image: String,
    val bannerImage: String,
    val duration: String,
    val name: String,
    val categories: String,
    val sinopsis: String,
    val stars: String,
    val director: String,
    val idDocument: String
)