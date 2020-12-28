package com.uabc.amc.cinemareview.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.components.HistoryFragmentMovie
import com.uabc.amc.cinemareview.components.MovieImageFragment
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.SQLiteService
import com.uabc.amc.cinemareview.utils.MathScreen
import kotlinx.android.synthetic.main.fragment_history_view.*
import kotlinx.android.synthetic.main.fragment_search_view.*

class HistoryViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        reload()
    }

    fun reload() {
        val movies = ArrayList<MovieImageFragment>()
        val userId = SQLiteService.getUser()[0]

        FirestoreCollection("user").document(userId).collection("history").orderBy("created").get().addOnSuccessListener {
                movieHistory -> movieHistory.documents.forEach {
                    val data = it.data

                    if (data != null) {
                        movies.add(MovieImageFragment(
                            data["document"] as String,
                            data["image"] as String,
                            data["cover"] as String,
                            data["duration"] as String,
                            data["name"] as String,
                            data["categories"] as String,
                            data["sinopsis"] as String,
                            data["stars"] as String,
                            data["director"] as String,
                            data["collection"] as String
                        ))
                    }
                }
            }.addOnCompleteListener {
                val items = movies.toList()

                history_item_list.apply {
                    resources.displayMetrics.apply {
                        val grids = MathScreen.dpScreenGridAdapter(widthPixels, density, MathScreen.WidthGrid)
                        layoutManager = GridLayoutManager(context, grids)
                    }
                    setHasFixedSize(true)
                    adapter = HistoryFragmentMovie(items, context)
                }

                if (movies.size > 0) { empty_result.visibility = View.INVISIBLE }
                else { empty_result.visibility = View.VISIBLE }
            }
    }
}