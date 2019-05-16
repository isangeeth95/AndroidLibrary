package com.example.ashimi.androidlibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "androidlibrary.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "f_name";
    public static final String COL_3 = "l_name";
    public static final String COL_4 = "address";
//    public static final String COL_5 = "tel_no";
    public static final String COL_5 = "email";
    public static final String COL_6 = "password";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, f_name TEXT," +
                "l_name TEXT, address TEXT, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String f_name, String l_name, String address, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name", f_name);
        contentValues.put("l_name", l_name);
        contentValues.put("address", address);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long res = db.insert("registeruser", null, contentValues);
        db.close();
         return res;
    }

    public Boolean checkUser(String email, String password){
        String[] columns = { COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_5 + "=?" + " and " + COL_6 + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0){
            return true;
        }
        else
            return false;
    }
}
