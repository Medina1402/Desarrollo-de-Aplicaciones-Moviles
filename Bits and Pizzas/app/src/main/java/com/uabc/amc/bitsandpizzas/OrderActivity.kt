package com.uabc.amc.bitsandpizzas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}