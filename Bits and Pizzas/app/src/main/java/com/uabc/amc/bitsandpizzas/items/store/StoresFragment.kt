package com.uabc.amc.bitsandpizzas.items.store

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

class StoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recycler = inflater.inflate(R.layout.fragment_store, container, false) as RecyclerView
        val names = Store.Stores.map { T -> T.getName() }.toTypedArray()
        val images =  Store.Stores.map { T -> T.getImageResourceId() }.toTypedArray()

        val adapter = CaptionImagesAdapter(names, images)
        recycler.adapter = adapter

        val layoutManager = GridLayoutManager(activity, 2)
        recycler.layoutManager = layoutManager

        adapter.setListener(object : CaptionImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, StoreDetailActivity::class.java)
                intent.putExtra(StoreDetailActivity.EXTRA_STORE_ID, position)
                activity?.startActivity(intent)
            }
        })

        return recycler
    }
}