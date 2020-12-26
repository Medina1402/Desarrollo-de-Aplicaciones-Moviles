package com.uabc.amc.cinemareview.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.uabc.amc.cinemareview.R

class ToastMessage(error: ERROR, context: Context) {
    companion object {
        enum class ERROR(private val error: Int) {
            EMPTY_USER(R.string.EMPTY_USER),
            INVALID_USER_LOGIN(R.string.INVALID_USER_LOGIN),
            ERROR_CONNECT_FIREBASE(R.string.ERROR_CONNECT_FIREBASE),
            REVIEW_UNSAVED(R.string.REVIEW_UNSAVED),
            REVIEW_EMPTY(R.string.REVIEW_EMPTY);

            fun toString(context: Context): String {
                return context.getString(error)
            }
        }
    }

    init {
        Toast.makeText(context, error.toString(context), 2000 as Int).apply {
            setGravity(Gravity.BOTTOM, 0, 20)
            show()
        }
    }
}