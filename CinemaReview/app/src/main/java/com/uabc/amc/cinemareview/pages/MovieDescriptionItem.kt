package com.uabc.amc.cinemareview.pages

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.Timestamp
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.MovieAddReviewDialogFragment
import com.uabc.amc.cinemareview.components.MovieReview
import com.uabc.amc.cinemareview.components.MovieReviewFragment
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.FirestoreFirebase
import com.uabc.amc.cinemareview.services.SQLiteService
import kotlinx.android.synthetic.main.activity_movie_description_item.*
import kotlinx.android.synthetic.main.item_movie_image_fragment_view.view.*
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MovieDescriptionItem : AppCompatActivity(), FirestoreFirebase {
    private var movieReviews = listOf<MovieReview>()
    private lateinit var idDocument: String
    private lateinit var idMovie: String
    private var commentReviewCurrentMovie = false

    private var stars = 0
    private var starsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description_item)

        // Value ID's Movie
        idDocument = intent.getStringExtra("MOVIE_ID_DOCUMENT")!!
        idMovie = intent.getStringExtra("MOVIE_ID")!!

        // Download data Firebase
        updateDataFirebaseFirestore()

        // get values intent and update view
        updateViewLayout()
    }

    private fun actionButtonReview() {
        // Action button
        if(commentReviewCurrentMovie) {
            movie_list_review_add_review.apply {
                text = "Editar reseña"
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
            }
        }

        movie_list_review_add_review.setOnClickListener {
            MovieAddReviewDialogFragment.display(supportFragmentManager, intent, this)
        }
    }

    fun deleteReview() {
        commentReviewCurrentMovie = false
        movie_list_review_add_review.apply {
            text = "Agregar reseña"
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccent))
        }
        intent.removeExtra("REVIEW_TEXT")
        intent.removeExtra("REVIEW_STARS")

        val userId = SQLiteService.getUser()[0]

        FirestoreCollection("user").document(userId).collection("history")
            .document(idMovie).delete()
    }

    private fun updateStarMovie() {
        var starsTemp = "0"
        if(stars!=0 && starsCount!=0) {
            starsTemp = (stars / starsCount).toString()
        }
        movie_description_stars.text = starsTemp
        FirestoreCollection("categories").document(idDocument)
            .collection("movies").document(idMovie).update("stars", starsTemp).isComplete
    }

    override fun updateDataFirebaseFirestore() {
        val reviews = ArrayList<MovieReview>()

        FirestoreCollection("categories").document(idDocument)
            .collection("movies").document(idMovie)
            .collection("reviews").get().addOnSuccessListener { review ->

                stars = 0
                starsCount = 0

                review.forEach {
                    val date = it.data["date"] as Timestamp
                    val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.US)

                    if(SQLiteService.getUser()[0].equals(it.id, false)) {
                        commentReviewCurrentMovie = true
                        intent.apply {
                            putExtra("REVIEW_TEXT", it.data["text"] as String)
                            putExtra("REVIEW_STARS", it.data["stars"] as String)
                        }
                    }

                    reviews.add(MovieReview(
                        it.id,
                        it.data["author"] as String,
                        formatDate.format(date.toDate().time) as String,
                        it.data["text"] as String,
                        it.data["stars"] as String
                    ))

                    stars += parseInt(it.data["stars"] as String)
                    starsCount++
                }
                updateStarMovie()
            }.addOnCompleteListener {
                movieReviews = reviews.toList()

                if(movieReviews.isNotEmpty()) {
                    movie_list_review_empty.visibility = View.INVISIBLE
                } else {
                    movie_list_review_empty.visibility = View.VISIBLE
                }

                // Adapter Movies Reviews
                movie_list_review_container.apply {
                    layoutManager = LinearLayoutManager(this@MovieDescriptionItem)
                    setHasFixedSize(true)
                    adapter = MovieReviewFragment(
                        movieReviews,
                        this@MovieDescriptionItem,
                        intent.getStringExtra("MOVIE_NAME") as String
                    )
                }

                updateStarMovie()
                actionButtonReview()
            }

        updateViewLayout()
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