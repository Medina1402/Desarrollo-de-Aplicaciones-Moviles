package com.uabc.amc.bitsandpizzas

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickDone(view: View) {
        Snackbar
            .make(view, "Your order has been updated", Snackbar.LENGTH_SHORT)
            .setAction("Undo", MySnackBarListener(this))
            .show()
    }

    private class MySnackBarListener(val ctx: Context): View.OnClickListener {
        override fun onClick(p0: View?) {
            val toast = Toast.makeText(ctx, "Undone!", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}