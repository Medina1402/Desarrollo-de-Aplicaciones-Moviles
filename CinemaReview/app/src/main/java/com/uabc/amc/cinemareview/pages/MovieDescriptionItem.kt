package com.uabc.amc.cinemareview.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.MovieReview
import com.uabc.amc.cinemareview.components.MovieReviewFragment
import com.uabc.amc.cinemareview.services.FirestoreFirebase
import kotlinx.android.synthetic.main.activity_movie_description_item.*

class MovieDescriptionItem : AppCompatActivity(), FirestoreFirebase {
    private var movieReviews = listOf<MovieReview>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description_item)

        // Intent extra values
        println(intent.getIntExtra("MOVIE_ID", 0))

        // Download data Firebase
        updateDataFirebaseFirestore()

        // Adapter Movies Reviews
        movie_list_review_container.apply {
            layoutManager = LinearLayoutManager(this@MovieDescriptionItem)
            setHasFixedSize(true)
            adapter = MovieReviewFragment(movieReviews, this@MovieDescriptionItem)
        }
    }

    override fun updateDataFirebaseFirestore() {
        // View Pager Adapter
        movieReviews = listOf(
            MovieReview("123465", "Abraham Medina Carrillo", "15/45/2020", "Pequeña reseña de lo que deben hacer", 5.4),
        )
    }
}