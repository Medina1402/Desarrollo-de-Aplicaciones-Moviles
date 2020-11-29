package com.uabc.amc.bitsandpizzas.items.store

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.uabc.amc.bitsandpizzas.R

class StoreDetailActivity : AppCompatActivity() {
    companion object {
        var EXTRA_STORE_ID: String = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_store)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val storeId = intent.extras?.get(EXTRA_STORE_ID) as Int
        val store = Store.Stores[storeId]
        val textView = findViewById<TextView>(R.id.store_text)
        textView.text = store.getName()

        val imageView = findViewById<ImageView>(R.id.store_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, store.getImageResourceId()))
        imageView.contentDescription = store.getName()

    }
}