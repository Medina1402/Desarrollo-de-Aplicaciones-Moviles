package com.uabc.amc.cinemareview.pages

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.services.FIREBASE_AUTH
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.SQLiteService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Search for record in SQLite
        try {
            beforeOnCreate()
        } catch (e: Error) {
            Toast.makeText(this, "signInWithEmail: ERROR DATABASE", (2000).toInt()).apply {
                setGravity(Gravity.BOTTOM, 0, 20)
            }.show()
        }

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
                        Toast.makeText(this, "Error de acceso", (2000).toInt())
                            .apply {
                                setGravity(Gravity.BOTTOM, 0, 20)
                            }.show()
                        return@addOnCompleteListener
                    }

                    val data = FIREBASE_AUTH.currentUser
                    if(data == null) {
                        Toast.makeText(this, "Error de data user login", (2000).toInt())
                            .apply {
                                setGravity(Gravity.BOTTOM, 0, 20)
                            }.show()
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

                }.addOnFailureListener {
                    Toast.makeText(this, it.toString(), (2000).toInt()).apply {
                        setGravity(Gravity.BOTTOM, 0, 20)
                    }.show()
                }
            } else {
                Toast.makeText(this, "USER or EMAIL is invalid value", (2000).toInt()).apply {
                    setGravity(Gravity.BOTTOM, 0, 20)
                }.show()
            }
        }

        btn_register.setOnClickListener {
            Intent(this, RegisterActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}