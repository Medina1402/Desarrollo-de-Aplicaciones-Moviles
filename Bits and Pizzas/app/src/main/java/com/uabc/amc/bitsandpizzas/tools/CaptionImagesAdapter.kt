package com.uabc.amc.bitsandpizzas.tools

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.uabc.amc.bitsandpizzas.R

class CaptionImagesAdapter(private var captions: Array<String>, private var imageIds: Array<Int>) :
    RecyclerView.Adapter<CaptionImagesAdapter.Companion.ViewHolder>() {

    interface Listener { fun onClick(position: Int) }

    companion object {
        class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {}
    }

    private var listener: Listener? = null

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return captions.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val cardView = LayoutInflater
            .from(p0.context)
            .inflate(R.layout.card_captioned_image, p0, false) as CardView

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cardView: CardView = p0.cardView
        val imageView = cardView.findViewById<ImageView>(R.id.info_image)
        val drawable = ContextCompat.getDrawable(cardView.context, imageIds[p1])

        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[p1]

        val textView = cardView.findViewById<TextView>(R.id.info_text)
        textView.text = captions[p1]

        cardView.setOnClickListener(MyCardViewListener(p1, listener))
    }

    private class MyCardViewListener(val p1: Int, val listener: Listener?): View.OnClickListener {
        override fun onClick(p0: View?) {
            listener?.onClick(p1)
        }
    }
}