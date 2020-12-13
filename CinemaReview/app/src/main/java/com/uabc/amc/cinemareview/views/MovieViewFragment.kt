package com.uabc.amc.cinemareview.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.*
import kotlinx.android.synthetic.main.fragment_movie_view.*

class MovieViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // View Pager Adapter
        val movieBanner = listOf(
            MovieViewPager("Interstelar", "1h 25min", "Accion, Sci-Fi, Drama", R.drawable.crakhaus),
            MovieViewPager("Gravity", "2h 2min", "Suspenso, Sci-Fi, Drama", R.drawable.crakhaus),
            MovieViewPager("Star Wars", "8h 12min", "Accion, Snimada, Sci-Fi", R.drawable.crakhaus),
        )
        view_pager_movie.adapter = MoviesViewPagerAdapter(movieBanner)

        // Scroll List Movies Adapter
        val movieScroll = listOf(
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
            MovieFragmentHorizontal("Recomendado por la prensa", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Aclamados por el publico", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Cine Mexicano", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Series Coreanas", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Anime", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
            MovieFragmentHorizontal("Para toda la familia", listOf(
                MovieImageFragment(123445, R.drawable.new_mutants),
                MovieImageFragment(123446, R.drawable.new_mutants),
                MovieImageFragment(123447, R.drawable.new_mutants),
                MovieImageFragment(123448, R.drawable.new_mutants),
                MovieImageFragment(123449, R.drawable.new_mutants),
            )),
        )
        val manager = LinearLayoutManager(context)
        movie_fragment_card_slide.layoutManager = manager
        movie_fragment_card_slide.setHasFixedSize(true)
        movie_fragment_card_slide.adapter =
            context?.let { MovieFragmentHorizontalView(movieScroll, it) }
    }
}