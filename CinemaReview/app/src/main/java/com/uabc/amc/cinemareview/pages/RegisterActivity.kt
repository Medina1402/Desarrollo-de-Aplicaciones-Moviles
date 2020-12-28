package com.uabc.amc.cinemareview.pages

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.services.FIREBASE_AUTH
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.SQLiteService
import com.uabc.amc.cinemareview.utils.ToastMessage
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
        if (savedInstanceState != null) {
            email_register.setText(savedInstanceState.getString("email").toString())
            username_register.setText(savedInstanceState.getString("username").toString())
            password_login.setText(savedInstanceState.getString("password").toString())
            password_confirm_register.setText(savedInstanceState.getString("password_confirm")
                .toString())
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
            if (
                email_register.text?.isNotEmpty()!! &&
                username_register.text?.isNotEmpty()!! &&
                password_register.text?.isNotEmpty()!! &&
                password_confirm_register.text?.isNotEmpty()!! &&
                password_confirm_register.text.toString()
                    .equals(password_register.text.toString(), false)
            ) {
                FIREBASE_AUTH.createUserWithEmailAndPassword(
                    email_register.text.toString(), password_register.text.toString()
                ).addOnCompleteListener { authResult ->
                    if (!authResult.isSuccessful) {
                        Toast.makeText(this, "sssssssssssss", (2000).toInt())
                            .apply {
                                setGravity(Gravity.BOTTOM, 0, 20)
                            }.show()
                        return@addOnCompleteListener
                    }
                    val data = FIREBASE_AUTH.currentUser
                    if(data == null) {
                        Toast.makeText(this, "axaxaxax", (2000).toInt())
                            .apply {
                                setGravity(Gravity.BOTTOM, 0, 20)
                            }.show()
                        return@addOnCompleteListener
                    }

                    val insert = hashMapOf(
                        "username" to username_register.text.toString(),
                        "email" to email_register.text.toString(),
                        "created" to Timestamp.now(),
                        "updated" to Timestamp.now()
                    )

                    FirestoreCollection("user").document(data.uid).set(insert)
                        .addOnCompleteListener {
                            SQLiteService.InsertUser(
                                data.uid,
                                username_register.text.toString(),
                                data.email.toString()
                            )

                            Intent(this, MoviesActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                .apply {
                                    startActivity(this)
                                    finish()
                                }

                        }.addOnFailureListener {
                            ToastMessage(ToastMessage.Companion.ERROR.INVALID_USER_LOGIN, this)
                        }
                }
            } else {
                ToastMessage(ToastMessage.Companion.ERROR.INVALID_USER_LOGIN, this)
            }
        }
    }
}