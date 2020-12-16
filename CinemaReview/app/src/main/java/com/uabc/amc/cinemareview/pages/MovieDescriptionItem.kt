package com.uabc.amc.cinemareview.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialDialogs
import com.google.firebase.Timestamp
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.MovieAddReviewDialogFragment
import com.uabc.amc.cinemareview.components.MovieReview
import com.uabc.amc.cinemareview.components.MovieReviewFragment
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.FirestoreFirebase
import kotlinx.android.synthetic.main.activity_movie_description_item.*
import kotlinx.android.synthetic.main.item_movie_image_fragment_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MovieDescriptionItem : AppCompatActivity(), FirestoreFirebase {
    private var movieReviews = listOf<MovieReview>()
    private lateinit var idDocument: String
    private lateinit var idMovie: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description_item)

        // Value ID's Movie
        idDocument = intent.getStringExtra("MOVIE_ID_DOCUMENT")!!
        idMovie = intent.getStringExtra("MOVIE_ID")!!

        // get values intent and update view
        updateViewLayout()

        // Download data Firebase
        updateDataFirebaseFirestore()

        // Action button
        movie_list_review_add_review.setOnClickListener {
            MovieAddReviewDialogFragment.display(supportFragmentManager)
        }
    }

    override fun updateDataFirebaseFirestore() {
        val reviews = ArrayList<MovieReview>()

        FirestoreCollection("categories").document(idDocument)
            .collection("movies").document(idMovie)
            .collection("reviews").get().addOnSuccessListener { review ->
                review.forEach {
                    val date = it.data["date"] as Timestamp
                    val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.US)

                    reviews.add(MovieReview(
                        it.id,
                        it.data["author"] as String,
                        formatDate.format(date.toDate().time) as String,
                        it.data["text"] as String,
                        it.data["stars"] as String
                    ))
                }

            }.addOnCompleteListener {
                movieReviews = reviews.toList()

                if(movieReviews.isNotEmpty()) {
                    movie_list_review_empty.visibility = View.INVISIBLE
                }

                // Adapter Movies Reviews
                movie_list_review_container.apply {
                    layoutManager = LinearLayoutManager(this@MovieDescriptionItem)
                    setHasFixedSize(true)
                    adapter = MovieReviewFragment(movieReviews, this@MovieDescriptionItem)
                }
            }
    }

    private fun updateViewLayout() {
        movie_description_title.text = intent.getStringExtra("MOVIE_NAME")
        movie_description_director.text = intent.getStringExtra("MOVIE_DIRECTOR")
        movie_description_stars.text = intent.getStringExtra("MOVIE_STARS")
        movie_description_categories.text = intent.getStringExtra("MOVIE_CATEGORIES")
        movie_description_sinopsis.text = intent.getStringExtra("MOVIE_SINOPSIS")
        movie_description_duration.text = intent.getStringExtra("MOVIE_DURATION")

        Glide.with(this).load(intent.getStringExtra("MOVIE_COVER")).into(movie_description_cover)
        Glide.with(this).load(intent.getStringExtra("MOVIE_BANNER")).into(movie_description_background)
    }
}