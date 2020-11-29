package com.uabc.amc.bitsandpizzas.items.store

import com.uabc.amc.bitsandpizzas.R

class Store private constructor(private var name: String, private var imageResourceId: Int) {
    companion object {
        val Stores = arrayOf(
            Store("Cambridge", R.drawable.cambridge),
            Store("Sebastopol", R.drawable.sebastopol)
        )
    }

    fun getName(): String {
        return name
    }

    fun getImageResourceId(): Int {
        return imageResourceId
    }
}