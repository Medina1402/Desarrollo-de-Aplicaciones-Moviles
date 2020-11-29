package com.uabc.amc.bitsandpizzas.items.pizza

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.uabc.amc.bitsandpizzas.R

class PizzaDetailActivity : AppCompatActivity() {
    companion object {
        var EXTRA_PIZZA_ID: String = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_pizza)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val pizzaId = intent.extras?.get(EXTRA_PIZZA_ID) as Int
        val pizza = Pizza.Pizzas[pizzaId]
        val textView = findViewById<TextView>(R.id.pizza_text)
        textView.text = pizza.getName()

        val imageView = findViewById<ImageView>(R.id.pizza_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pizza.getImageResourceId()))
        imageView.contentDescription = pizza.getName()

    }
}