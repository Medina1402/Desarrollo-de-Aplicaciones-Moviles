package com.uabc.amc.bitsandpizzas.items.pasta

import com.uabc.amc.bitsandpizzas.R

class Pasta private constructor(private var name: String, private var imageResourceId: Int) {
    companion object {
        val Pastas = arrayOf(
            Pasta("Spaghetti Bolognese", R.drawable.spaghetti),
            Pasta("Lasagna", R.drawable.lasagna)
        )
    }

    fun getName(): String {
        return name
    }

    fun getImageResourceId(): Int {
        return imageResourceId
    }
}