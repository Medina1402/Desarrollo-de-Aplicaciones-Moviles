package com.uabc.amc.cinemareview.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ToastMessage {
    public static enum ERROR {
        INVALID_USER_LOGIN("asdasd");

        private String error;
        ERROR(String error) {
            this.error = error;
        }

        @NonNull
        @Override
        public String toString() {
            return error;
        }
    }

    public ToastMessage(ERROR error, Context context) {
        Toast toast = Toast.makeText(context, error.toString(), (int) 2000);
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.show();
    }
}
