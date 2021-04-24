package com.android.server;

import android.content.Context;
import android.app.IPhonebookService;
import android.os.RemoteException;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import com.android.server.PhonebookServiceHelper;
import java.util.*;

public class PhonebookService extends IPhonebookService.Stub {

    static final String TAG = "Phonebook";
    private final Object mLock = new Object();
    private final Context mContext;
    private boolean statusFile;

    private PhonebookServiceHelper mDbHelper = null;
    private SQLiteDatabase phonebookDB = null;
    
    public PhonebookService(Context context) {
        Log.i(TAG, "Constructor PhonebookService!!! ");
        mContext = context;
        phonebookDB = getDatabase(context);
    }

    protected PhonebookServiceHelper getDatabaseHelper(Context context) {
        if (mDbHelper == null) {
            mDbHelper = new PhonebookServiceHelper(context);
        }
        return mDbHelper;
    }

    protected SQLiteDatabase getDatabase(Context context) {
        if (phonebookDB == null) {
            phonebookDB = getDatabaseHelper(context).getWritableDatabase();
        }
        return phonebookDB;
    }

    @Override
    public boolean verifyIfNumberExistOnWhileList(String number) {
        phonebookDB = getDatabase(mContext);
        String sqlQuery = "SELECT * FROM WhiteList WHERE contactNumber=" + number;
        Cursor cursor = this.phonebookDB.rawQuery(sqlQuery, null);
        if (cursor.moveToNext()) {
            Log.i(TAG, "Number " + number + " exist in WhiteList!");
            return true;
        }
        Log.i(TAG, "Number " + number + " not exist in Whitelist!");
        return false;
    }

    @Override
    public String getNumberOnWhiteList(String number) throws RemoteException {
        phonebookDB = getDatabase(mContext);
        String sqlQuery = "SELECT * FROM WhiteList WHERE contactNumber=" + number;
        Cursor cursor = this.phonebookDB.rawQuery(sqlQuery, null);
        if (cursor.moveToNext()) {
            Log.i(TAG, "Number " + number + " exist in Whitelist!");
            return cursor.getString(0);
        }
        Log.i(TAG, "Number " + number + " not exist in Whitelist!");
        return "";
    }

    @Override
    public boolean setNumberOnWhiteList(String number) throws RemoteException {
        phonebookDB = getDatabase(mContext);
        try {
           String sqlQuery = "INSERT INTO WhiteList VALUES ('"+number+"')";
           this.phonebookDB.execSQL(sqlQuery);
           Log.i(TAG, "Add " + number + " in to Whitelist");
           return true;
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    @Override
    public String[] getAllNumbersOnWhiteList() throws RemoteException {
        phonebookDB = getDatabase(mContext);
        ArrayList<String> str_ArraysList = new ArrayList<String>();
        String sqlQuery = "SELECT contactNumber FROM WhiteList";
        Cursor cursor = this.phonebookDB.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            str_ArraysList.add(cursor.getString(0));
        }
        String str_Array[] = str_ArraysList.toArray(new String[str_ArraysList.size()]);
        Log.i(TAG, "Get all numbers of the WhiteList");
        return str_Array;
    }

    @Override
    public boolean updateNumberOnWhiteList(String number, String newNumber) throws RemoteException {
        if (verifyIfNumberExistOnWhileList(number)) {
            try {
                String sqlQuery = "UPDATE WhiteList SET contactNumber='"+newNumber+"' WHERE contactNumber='"+number+"'";
                this.phonebookDB.execSQL(sqlQuery);
                Log.i(TAG, "Update " + number + " to " + newNumber + " in to Whitelist");
                return true;
            } catch(SQLException e) {
                Log.e(TAG, e.getMessage());
                return false;
            }
        } 
        return false;
    }

    @Override
    public boolean deleteNumberOnWhiteList(String number) throws RemoteException {
        if (verifyIfNumberExistOnWhileList(number)) {
            try {
                String sqlQuery = "DELETE FROM WhiteList WHERE contactNumber='"+number+"'";
                this.phonebookDB.execSQL(sqlQuery);
                Log.i(TAG, "Delete " + number + " in to Whitelist");
                return true;
            } catch(SQLException e) {
                Log.e(TAG, e.getMessage());
                return false;
            }
        }
        return false;
    }
}