package com.uabc.amc.cinemareview.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.pages.LoginActivity
import com.uabc.amc.cinemareview.pages.MoviesActivity
import com.uabc.amc.cinemareview.services.SQLiteService
import kotlinx.android.synthetic.main.fragment_account_view.*

class AccountViewFragment(private val parent: MoviesActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = SQLiteService.getUser()
        account_username.text = user[1]
        account_email.text = user[2]


        btn_logout.setOnClickListener {
            SQLiteService.DeleteUser(context)

            Intent(context, LoginActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .apply {
                    startActivity(this)
                    parent.finish()
                }
        }
    }

}