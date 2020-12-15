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
        FirestoreCollection("movie_banner").get()
            .addOnSuccessListener { result ->
                val data: ArrayList<MovieViewPager> = ArrayList()
                for (document in result) {

                    data.add(MovieViewPager(
                        document.data["name"] as String,
                        document.data["duration"] as String,
                        document.data["categories"] as String,
                        document.data["image"] as String,
                    ))
                }

                movieBanner = data.toList()

                // View Pager Adapter
                view_pager_movie.adapter = MoviesViewPagerAdapter(movieBanner)
            }
    }

    private fun onLoadMovieScroll() {
        FirestoreCollection("categories").get()
            .addOnSuccessListener { result ->

                val data: ArrayList<MovieFragmentHorizontal> = ArrayList()
                for (document in result) {

                    val movies: ArrayList<MovieImageFragment> = ArrayList()
                    // Movies in
                    FirestoreCollection("categories").document(document.id).collection("movies").get().addOnSuccessListener { snapshot ->
                        for(movie in snapshot) {
                            movies.add(MovieImageFragment(movie.id, movie.data["cover"] as String))
                        }
                    }

                    data.add(MovieFragmentHorizontal(document.data["name"] as String, movies.toList()))
                }

                movieScroll = data.toList()

                // Scroll List Movies Adapter
                movie_fragment_card_slide.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = context?.let { MovieFragmentHorizontalView(movieScroll, it) }
                }
            }
    }
}