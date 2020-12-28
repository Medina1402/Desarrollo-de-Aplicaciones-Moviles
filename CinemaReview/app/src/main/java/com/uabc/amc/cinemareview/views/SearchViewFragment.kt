package com.uabc.amc.cinemareview.views

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.HistoryFragmentMovie
import com.uabc.amc.cinemareview.components.MovieImageFragment
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.utils.MathScreen
import com.uabc.amc.cinemareview.utils.MathScreen.Companion.WidthGrid
import kotlinx.android.synthetic.main.fragment_search_view.*

class SearchViewFragment : Fragment() {
    companion object {
        var titleSearch = ""
    }
    private var movies = ArrayList<MovieImageFragment>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_view, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movies = ArrayList<MovieImageFragment>()
        FirestoreCollection("movies").get().addOnSuccessListener {
            it.documents.forEach { documentSnapshot ->
                val data = documentSnapshot.data ?: return@addOnSuccessListener

                movies.add(MovieImageFragment(
                    data["document"] as String,
                    data["cover"] as String,
                    data["image"] as String,
                    data["duration"] as String,
                    data["name"] as String,
                    data["categories"] as String,
                    data["sinopsis"] as String,
                    data["stars"] as String,
                    data["director"] as String,
                    data["collection"] as String
                ))
            }
        }.addOnCompleteListener { onResume() }
    }

    override fun onResume() {
        super.onResume()

        if(search_title_movie.text.toString().isNotEmpty()) searchEvent()
        else setAdapterSearchMovie(movies.toList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(context != null) {
            setAdapterSearchMovie(listOf()) // Load view
            search_movie_result.clearOnScrollListeners()
            search_title_movie.addTextChangedListener {
                searchEvent() // Change
            }
        }
    }

    private fun searchEvent() {
        val result = ArrayList<MovieImageFragment>()
        titleSearch = search_title_movie.text.toString().toLowerCase()
        val regex = Regex(pattern = titleSearch)

        movies.forEach {
            val matched = regex.containsMatchIn(input = it.name.toString().toLowerCase())
            if(matched) result.add(it)
        }

        setAdapterSearchMovie(result.toList())
    }

    private fun setAdapterSearchMovie(result: List<MovieImageFragment>) {
        resources.displayMetrics.apply {
            val grids = MathScreen.dpScreenGridAdapter(widthPixels, density, WidthGrid)
            search_movie_result.layoutManager = GridLayoutManager(context, grids)
        }

        if(result.isNotEmpty() && context != null) {
            search_movie_result.adapter = HistoryFragmentMovie(result, requireContext())
        }
    }
}