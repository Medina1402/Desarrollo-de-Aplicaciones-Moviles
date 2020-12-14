package com.uabc.amc.cinemareview.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.*
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.FirestoreFirebase
import kotlinx.android.synthetic.main.fragment_movie_view.*

class MovieViewFragment : Fragment(), FirestoreFirebase {
    private var movieBanner = listOf<MovieViewPager>()
    private var movieScroll = listOf<MovieFragmentHorizontal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Download data Firebase
        updateDataFirebaseFirestore()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // View Pager Adapter
        view_pager_movie.adapter = MoviesViewPagerAdapter(movieBanner)
        // Scroll List Movies Adapter
        movie_fragment_card_slide.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = context?.let { MovieFragmentHorizontalView(movieScroll, it) }
        }
    }

    override fun updateDataFirebaseFirestore() {
        FirestoreCollection("categories").get().addOnCompleteListener { it.result?.documents?.map { doc -> println(doc.data) } }

        movieBanner = listOf(
            MovieViewPager("Interstelar", "1h 25min", "Accion, Sci-Fi, Drama", R.drawable.crakhaus),
            MovieViewPager("Gravity", "2h 2min", "Suspenso, Sci-Fi, Drama", R.drawable.crakhaus),
            MovieViewPager("Star Wars", "8h 12min", "Accion, Snimada, Sci-Fi", R.drawable.crakhaus),
        )

        movieScroll = listOf(
            MovieFragmentHorizontal("Destacados de dia", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Destacados de dia", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Destacados de dia", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Destacados de dia", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
        )
    }
}