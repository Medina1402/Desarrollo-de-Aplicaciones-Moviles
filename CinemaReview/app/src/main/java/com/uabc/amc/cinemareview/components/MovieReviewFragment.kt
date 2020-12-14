package com.uabc.amc.cinemareview.components

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uabc.amc.cinemareview.R
import kotlinx.android.synthetic.main.item_movie_review_fragment.view.*

class MovieReviewFragment(private val reviewList: List<MovieReview>, private val context: Context) : RecyclerView.Adapter<MovieReviewFragment.MovieReviewFragmentHolder>() {
    inner class MovieReviewFragmentHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewFragmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_review_fragment, parent, false)
        return MovieReviewFragmentHolder(view)
    }

    override fun onBindViewHolder(holder: MovieReviewFragmentHolder, position: Int) {
        val review = reviewList[position]
        holder.itemView.movie_review_username.text = review.author
        holder.itemView.movie_review_date.text = review.date
        holder.itemView.movie_review_stars.text = review.stars.toString()
        holder.itemView.movie_review_text.text = review.review
        holder.itemView.movie_review_shared.setOnClickListener {
            val intentShared = Intent(Intent.ACTION_SEND)
            Intent.FLAG_ACTIVITY_NEW_TASK.also { intentShared.flags = it }
            intentShared.type = "text/plain"
            intentShared.putExtra(Intent.EXTRA_TEXT, review.review)
            context.startActivity(intentShared)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}

class MovieReview(val id: String, val author: String, val date: String, val review: String, val stars: Number)