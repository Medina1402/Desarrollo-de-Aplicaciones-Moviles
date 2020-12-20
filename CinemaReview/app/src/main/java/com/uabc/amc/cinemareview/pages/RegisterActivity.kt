package com.uabc.amc.cinemareview.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uabc.amc.cinemareview.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // stores application state
        onLoadSavedInstanceState(savedInstanceState)

        setup()
    }

    private fun onLoadSavedInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState != null) {
            email_register.setText(savedInstanceState.getString("email").toString())
            username_register.setText(savedInstanceState.getString("username").toString())
            password_login.setText(savedInstanceState.getString("password").toString())
            password_confirm_register.setText(savedInstanceState.getString("password_confirm").toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("email", email_register.text.toString())
        outState.putString("username", username_register.text.toString())
        outState.putString("password", password_register.text.toString())
        outState.putString("password_confirm", password_confirm_register.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun setup() {
        btn_register_action.setOnClickListener {

        }
    }
}