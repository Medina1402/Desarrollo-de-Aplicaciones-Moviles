package com.uabc.amc.bitsandpizzas.items.pizza

import com.uabc.amc.bitsandpizzas.R

class Pizza private constructor(private var name: String, private var imageResourceId: Int) {
    companion object {
        val Pizzas = arrayOf(
            Pizza("Diavalo", R.drawable.diavola),
            Pizza("Funghi", R.drawable.funghi)
        )
    }

    fun getName(): String {
        return name
    }

    fun getImageResourceId(): Int {
        return imageResourceId
    }
}