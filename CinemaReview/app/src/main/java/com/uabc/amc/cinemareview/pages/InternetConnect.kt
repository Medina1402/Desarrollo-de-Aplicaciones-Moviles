package com.uabc.amc.cinemareview.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uabc.amc.cinemareview.R

class InternetConnect : AppCompatActivity() {
    companion object {
        fun isOnlineNetwork(): Boolean {
            val process: Process = Runtime.getRuntime().exec("ping -c 1 www.google.com")
            val response = process.waitFor()
            return response == 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_connect)
    }
}