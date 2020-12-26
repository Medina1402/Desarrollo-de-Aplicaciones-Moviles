package com.uabc.amc.cinemareview.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.uabc.amc.cinemareview.R;

public class ToastMessage2 {
    public static enum ERROR {
        INVALID_USER_LOGIN(R.string.app_name);

        private int error;
        ERROR(int error) { this.error = error; }

        @NonNull
        public String toString(Context context) {
            return context.getString(error);
        }
    }

    public ToastMessage2(ERROR error, Context context) {
        Toast toast = Toast.makeText(context, error.toString(context), (int) 2000);
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.show();
    }
}
