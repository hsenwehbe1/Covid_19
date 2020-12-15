package com.example.covid_19

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by User on 2/28/2017.
 */
class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable =
            "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL2 + " TEXT, " + COL3 + " INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP IF TABLE EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addData(name: String, number: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL2, name)
        contentValues.put(COL3, number)
        Log.d(
            TAG,
            "addData: Adding $name and $number to $TABLE_NAME"
        )
        val result =
            db.insert(TABLE_NAME, null, contentValues)

        //if date as inserted incorrectly it will return -1
        return if (result == -1L) {
            return false
        } else {
            return true
        }
    }

    /**
     * Returns all the data from database
     *
     * @return
     */
    fun getData(number: Int): Cursor {
        val db = this.readableDatabase
        val query =
            "SELECT " + COL3 +" , "+COL2+" FROM " + TABLE_NAME +
                    " WHERE " + COL3 + " = '" + number + "'"
        return db.rawQuery(query, null)
    }

    companion object {
        private const val TAG = "DatabaseHelper"
        const val DATABASE_NAME = "Wifi.db"
        private const val TABLE_NAME = "people_table"
        private const val COL2 = "name"
        private const val COL3 = "number"
    }
}