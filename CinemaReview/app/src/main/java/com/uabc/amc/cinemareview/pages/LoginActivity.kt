package com.uabc.amc.cinemareview.pages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.services.FIREBASE_AUTH
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.SQLiteService
import com.uabc.amc.cinemareview.utils.ToastMessage
import com.uabc.amc.cinemareview.utils.ToastMessage2
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Search for record in SQLite and status connection internet
        try {
            if(!InternetConnect.isOnlineNetwork()) {
                // Insert a stack Activities and remove before Activities
                Intent(this, InternetConnect::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    .apply {
                        setTheme(R.style.AppTheme)
                        startActivity(this)
                        finish()
                    }
            } else beforeOnCreate()

        } catch (e: Error) {}

        // Default event
        super.onCreate(savedInstanceState)

        // Load activity layout
        setContentView(R.layout.activity_login)

        // stores application state
        onLoadSavedInstanceState(savedInstanceState)

        // Event of buttons
        setup()
    }

    private fun onLoadSavedInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState != null) {
            email_login.setText(savedInstanceState.getString("email").toString())
            password_login.setText(savedInstanceState.getString("email").toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("email", email_login.text.toString())
        outState.putString("password", password_login.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun beforeOnCreate() {
        // if not exist user in SQLite, show login
        if (!SQLiteService.isExistUser(this)) return

        // Insert a stack Activities and remove before Activities
        Intent(this, MoviesActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            .apply {
                setTheme(R.style.AppTheme)
                startActivity(this)
                finish()
            }
    }

    private fun setup() {
        btn_login.setOnClickListener {
            if (email_login.text?.isNotEmpty()!! && password_login.text?.isNotEmpty()!!) {
                FIREBASE_AUTH.signInWithEmailAndPassword(
                    email_login.text.toString(),
                    password_login.text.toString()
                ).addOnCompleteListener {
                    if(!it.isSuccessful) {
                        ToastMessage(ToastMessage.Companion.ERROR.ERROR_CONNECT_FIREBASE, this)
                        return@addOnCompleteListener
                    }

                    val data = FIREBASE_AUTH.currentUser
                    if(data == null) {
                        ToastMessage(ToastMessage.Companion.ERROR.INVALID_USER_LOGIN, this)
                        return@addOnCompleteListener
                    }

                    SQLiteService.RegisterUser(
                        data.uid,
                        FirestoreCollection("user").document(data.uid),
                        this
                    )

                    Intent(this, MoviesActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .apply {
                            startActivity(this)
                            finish()
                        }

                }
            } else {
                ToastMessage(ToastMessage.Companion.ERROR.EMPTY_USER, this)
            }
        }

        btn_register.setOnClickListener {
            Intent(this, RegisterActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}