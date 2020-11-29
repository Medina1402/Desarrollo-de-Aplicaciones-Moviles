package com.uabc.amc.bitsandpizzas.items.pasta

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.uabc.amc.bitsandpizzas.R

class PastaDetailActivity : AppCompatActivity() {
    companion object {
        var EXTRA_PASTA_ID: String = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_pasta)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val pastaId = intent.extras?.get(EXTRA_PASTA_ID) as Int
        val pasta = Pasta.Pastas[pastaId]
        val textView = findViewById<TextView>(R.id.pasta_text)
        textView.text = pasta.getName()

        val imageView = findViewById<ImageView>(R.id.pasta_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pasta.getImageResourceId()))
        imageView.contentDescription = pasta.getName()

    }
}