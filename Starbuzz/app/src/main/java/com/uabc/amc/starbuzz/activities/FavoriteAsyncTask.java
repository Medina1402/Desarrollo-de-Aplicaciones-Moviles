package com.uabc.amc.starbuzz.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.uabc.amc.starbuzz.database.StarbuzzDatabaseHelper;

@SuppressWarnings("deprecation")
public class FavoriteAsyncTask extends AsyncTask<Integer, Void, Boolean> {
    private ContentValues values;
    @SuppressLint("StaticFieldLeak") private CheckBox checkBox;
    @SuppressLint("StaticFieldLeak") private Context context;
    private String tableModel;


    public FavoriteAsyncTask(View checkBox, Context context, String tableModel) {
        this.checkBox = (CheckBox) checkBox;
        this.context = context;
        this.tableModel = tableModel;

    }


    @Override
    protected void onPreExecute() {
        values = new ContentValues();
        values.put("favorite", checkBox.isChecked());
    }


    @Override
    protected Boolean doInBackground(Integer... integers) {
        int _id = integers[0];

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(context);
        try {
            SQLiteDatabase database = starbuzzDatabaseHelper.getWritableDatabase();
            database.update(tableModel, values, "_id = ?", new String[] {Integer.toString(_id)});
            database.close();
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(!aBoolean) {
            Toast toast = Toast.makeText(context, tableModel + " Activity - Database unavailable checkbox", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
