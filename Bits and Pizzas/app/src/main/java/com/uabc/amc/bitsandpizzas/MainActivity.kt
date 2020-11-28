package com.uabc.amc.bitsandpizzas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private var sharedActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
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
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}