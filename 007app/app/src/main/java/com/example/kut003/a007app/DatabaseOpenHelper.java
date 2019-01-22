package com.example.kut003.a007app;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";
    private static final String DATABASE_NAME = "grow.db";
    public static final String TABLE_NAME = "images";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        createScheduleTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);

    }

    private void createScheduleTable(SQLiteDatabase db){
        // テーブル作成SQL
        String sql = "CREATE TABLE images ("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " Path TEXT,"
                + " Comment TEXT"
                + ");";
        db.execSQL(sql);
        Log.i(TAG, "テーブルimagesが作成されました");
    }
}
