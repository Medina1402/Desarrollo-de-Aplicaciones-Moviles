package com.uabc.amc.bitsandpizzas.items.pasta

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uabc.amc.bitsandpizzas.R
import com.uabc.amc.bitsandpizzas.tools.CaptionImagesAdapter

class PastaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recycler = inflater.inflate(R.layout.fragment_pasta, container, false) as RecyclerView
        val names = Pasta.Pastas.map { T -> T.getName() }.toTypedArray()
        val images =  Pasta.Pastas.map { T -> T.getImageResourceId() }.toTypedArray()

        val adapter = CaptionImagesAdapter(names, images)
        recycler.adapter = adapter

        val layoutManager = GridLayoutManager(activity, 2)
        recycler.layoutManager = layoutManager

        adapter.setListener(object : CaptionImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, PastaDetailActivity::class.java)
                intent.putExtra(PastaDetailActivity.EXTRA_PASTA_ID, position)
                activity?.startActivity(intent)
            }
        })

        return recycler
    }
}