package com.uabc.amc.cinemareview.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.*
import com.uabc.amc.cinemareview.services.*
import kotlinx.android.synthetic.main.fragment_movie_view.*

class MovieViewFragment : Fragment(), FirestoreFirebase {
    private var movieBanner = listOf<MovieViewPager>()
    private var movieScroll = listOf<MovieFragmentHorizontal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDataFirebaseFirestore()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_view, container, false)
    }

    override fun updateDataFirebaseFirestore() {
        onLoadMovieScroll()
        onLoadMovieBanner()
    }

    private fun onLoadMovieBanner() {
        val data: ArrayList<MovieViewPager> = ArrayList()

        FirestoreCollection("movie_banner").get()
            .addOnSuccessListener { result ->
                result.forEach { document ->
                    data.add(MovieViewPager(
                        document.data["name"] as String,
                        document.data["duration"] as String,
                        document.data["categories"] as String,
                        document.data["image"] as String,
                    ))
                }
            }.addOnCompleteListener {
                if(it.isSuccessful) {
                    movieBanner = data.toList()

                    // View Pager Adapter
                    view_pager_movie.adapter = MoviesViewPagerAdapter(movieBanner)
                }
            }
    }

    private fun onLoadMovieScroll() {
        val query = FirestoreCollection("categories")

        query.get().addOnSuccessListener { result ->
            val data: ArrayList<MovieFragmentHorizontal> = ArrayList()
            result.forEach { document ->
                val movies: ArrayList<MovieImageFragment> = ArrayList()
                query.document(document.id).collection("movies").get().addOnSuccessListener { snapshot ->
                    snapshot.forEach { movie ->

                        movies.add(MovieImageFragment(
                            movie.id,
                            movie.data["cover"] as String,
                            movie.data["image"] as String,
                            movie.data["duration"] as String,
                            movie.data["name"] as String,
                            movie.data["categories"] as String,
                            movie.data["sinopsis"] as String,
                            movie.data["stars"] as String,
                            movie.data["director"] as String,
                            document.id
                        ))
                    }
                }.addOnCompleteListener {
                    if(it.isSuccessful && it.isComplete) {
                        data.add(MovieFragmentHorizontal(document.data["name"] as String, movies.toList()))
                        movieScroll = data.toList()

                       if(context != null) {
                           // Scroll List Movies Adapter
                           movie_fragment_card_slide.apply {
                               layoutManager = LinearLayoutManager(context)
                               setHasFixedSize(true)
                               adapter = MovieFragmentHorizontalView(movieScroll, context)
                           }
                       }
                    }
                }
            }
        }
    }
}