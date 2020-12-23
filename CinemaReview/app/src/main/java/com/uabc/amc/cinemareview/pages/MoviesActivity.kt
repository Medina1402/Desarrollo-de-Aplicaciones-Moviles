package com.uabc.amc.cinemareview.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.views.AccountViewFragment
import com.uabc.amc.cinemareview.views.HistoryViewFragment
import com.uabc.amc.cinemareview.views.MovieViewFragment
import com.uabc.amc.cinemareview.views.SearchViewFragment
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {
    private val movieViewFragment = MovieViewFragment()
    private val searchViewFragment = SearchViewFragment()
    private val historyViewFragment = HistoryViewFragment()
    private val accountViewFragment = AccountViewFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        // First instance for Container Fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container_view_frame, MovieViewFragment::class.java, null)
                .commit()
        }

        // Listen Select Item Bottom Navigation
        bottom_navigation.setOnNavigationItemSelectedListener { item -> selectItem(item) }
    }

    /**
     * Change Container of Fragment
     * @param item Item Select on Click Bottom Navigation
     */
    private fun selectItem(item: MenuItem): Boolean {
        val classItem = when(item.itemId) {
            R.id.item_navigation_movie -> movieViewFragment
            R.id.item_navigation_search -> searchViewFragment
            R.id.item_navigation_history -> historyViewFragment
            R.id.item_navigation_account -> accountViewFragment
            else -> null
        } ?: return false

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.container_view_frame, classItem, null)
            .commit()
        return true
    }
}