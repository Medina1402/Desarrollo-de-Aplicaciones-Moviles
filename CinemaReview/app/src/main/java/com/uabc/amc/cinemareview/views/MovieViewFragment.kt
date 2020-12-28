package com.uabc.amc.cinemareview.views

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.*
import com.uabc.amc.cinemareview.services.*
import kotlinx.android.synthetic.main.fragment_movie_view.*
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.system.measureTimeMillis

class MovieViewFragment : Fragment() {
    private var movieBanner = ArrayList<MovieImageFragment>()
    private var movieScroll = listOf<MovieFragmentHorizontal>()
    private lateinit var runnable: Runnable
    private var counter = 1
    private var increment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onLoadMovieScroll()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_view, container, false)
    }

    private fun onLoadMovieBannerRecycler() {
        // View Pager Adapter
        val adapter = MoviesViewPagerAdapter(movieBanner.toList())
        view_pager_movie.adapter = adapter
        delayNextViewPager()
    }


    private fun delayNextViewPager() {
        val handler = Handler()
        runnable = Runnable {
            try {
                if(view_pager_movie != null) {
                    if(view_pager_movie.adapter?.itemCount!! <= view_pager_movie.currentItem+1) increment = -1
                    else if(view_pager_movie.currentItem == 0) increment = 1
                    view_pager_movie.currentItem += increment
                }
                handler.postDelayed(runnable, 10000)
            } catch (e: Error){}
        }
        handler.post(runnable)
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
                        movieScroll.forEach {
                            for (movie in it.movies) {
                                movieBanner.add(movie)
                            }
                        }

                       if(context != null) {
                           // Scroll List Movies Adapter
                           movie_fragment_card_slide.apply {
                               layoutManager = LinearLayoutManager(context)
                               setHasFixedSize(true)
                               adapter = MovieFragmentHorizontalView(movieScroll, context)
                           }

                           if(--counter >= 0) onLoadMovieBannerRecycler()
                       }
                    }
                }
            }
        }
    }
}