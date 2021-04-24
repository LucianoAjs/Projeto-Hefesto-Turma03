package com.android.server;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.Context;

public class PhonebookServiceHelper extends SQLiteOpenHelper {
    
    static final String TAG = "Phonebook";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Phonebook.db";
    private static final String SQL_CREATE_TABLES = "CREATE TABLE WhiteList (contactNumber TEXT PRIMARY KEY)";
    // private static final String SQL_POPULATE_TABLES = "INSERT INTO WhiteList VALUES ('123')";
    private static final String SQL_DELETE_TABLES = "DROP TABLE IF EXISTS WhiteList";
    
    public PhonebookServiceHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(TAG, "Constructor PhonebookServiceHelper!!!");
    }

    @Override 
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Create DATABASE");
        db.execSQL(SQL_CREATE_TABLES);
        // db.execSQL(SQL_POPULATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrade DATABASE");
        db.execSQL(SQL_DELETE_TABLES);
        onCreate(db);
    } 
}
