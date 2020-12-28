package com.uabc.amc.cinemareview.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.Gravity
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference

class SQLiteService {
    companion object {
        private var DatabaseHelper: createDataBase? = null

        fun RegisterUser(userId: String, collection: DocumentReference, context: Context) {
            collection.get()
                .addOnCompleteListener {
                    val data = it.result?.data
                    if (data != null) {
                        InsertUser(
                            userId,
                            data["username"] as String,
                            data["email"] as String
                        )
                    } else {
                        Toast.makeText(context, "signInWithEmail: ERROR DATA", (2000).toInt()).apply {
                            setGravity(Gravity.BOTTOM, 0, 20)
                        }.show()
                    }
                }
        }

        fun InsertUser(_id: String, username: String, email: String) {
            val helper = DatabaseHelper?.writableDatabase?: return

            val contentValues = ContentValues()
            contentValues.apply {
                put("_id", _id)
                put("username", username)
                put("email", email)
            }

            helper.insert("USER", null, contentValues)
            helper.close()
        }

        fun DeleteUser(context: Context?) {
                DatabaseHelper = createDataBase(context)
                val helper = this.DatabaseHelper?.writableDatabase
                helper?.execSQL("delete from USER")
                helper?.close()
        }

        fun isExistUser(context: Context): Boolean {
            DatabaseHelper = createDataBase(context)
            val helper = DatabaseHelper?.readableDatabase ?: return false
            val columns: Array<String> = arrayOf("_id")
            val cursor = helper.query("USER", columns,
                null, null, null, null, null)

            if(cursor.count == 0) return false
            else {
                cursor.moveToFirst()
                val temp = cursor.getString(cursor.getColumnIndex("_id")).length > 10
                cursor.close()
                helper.close()
                return temp
            }
        }

        fun getUser(): List<String> {
            val helper = DatabaseHelper?.readableDatabase
            val columns: Array<String> = arrayOf("_id", "username", "email")
            val cursor = helper?.query("USER", columns,
                null, null, null, null, null)
            val values = ArrayList<String>()

            cursor?.moveToFirst()
            values.add(cursor?.getString(cursor.getColumnIndex("_id")).toString())
            values.add(cursor?.getString(cursor.getColumnIndex("username")).toString())
            values.add(cursor?.getString(cursor.getColumnIndex("email")).toString())
            cursor?.close()
            helper?.close()
            return values.toList()
        }
    }


    private class createDataBase(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            val DATABASE_NAME = "MovieReview"
            val DATABASE_VERSION = 1
        }

        override fun onCreate(p0: SQLiteDatabase?) {
            p0?.execSQL("" +
                    "CREATE TABLE IF NOT EXISTS USER(" +
                        "_id varchar(255) primary key, " +
                        "username varchar(255), " +
                        "email varchar(255) " +
                    ");")
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0?.execSQL("DROP TABLE IF EXISTS USER");
            onCreate(p0)
        }

    }
}