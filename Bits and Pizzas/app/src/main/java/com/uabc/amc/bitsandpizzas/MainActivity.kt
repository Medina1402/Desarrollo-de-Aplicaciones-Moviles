package com.uabc.amc.bitsandpizzas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    private var sharedActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager, this)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = pagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.action_share)
        sharedActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider?
        setShareActionIntent("Want to join me for pizza?")

        return super.onCreateOptionsMenu(menu)
    }

    private fun setShareActionIntent(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        sharedActionProvider?.setShareIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_create_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private class SectionsPagerAdapter(fm: FragmentManager, val ctx: Context) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return 4
        }

        override fun getItem(p0: Int): Fragment? {
            return when(p0) {
                0 -> TopFragment()
                1 -> PizzaFragment()
                2 -> PastaFragment()
                3 -> StoresFragment()
                else -> null
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> ctx.getString(R.string.home_tab)
                1 -> ctx.getString(R.string.pizza_tab)
                2 -> ctx.getString(R.string.pasta_tab)
                3 -> ctx.getString(R.string.store_tab)
                else -> null
            }
        }

    }
}