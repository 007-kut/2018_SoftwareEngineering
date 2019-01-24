package com.example.kut003.a007app;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";
    //DB version
    public static final int DATABASE_VERSION = 1;
    //DB name
    public static final String DATABASE_NAME = "imageDB.db";
    //Table name
    public static final String TABLE_NAME = "images";

    //public static final String COLUMN_NAME_TITLE = "uriPath";
    //public static final String COLUMN_NAME_SUBTITLE = "comment";

    private static final String SQL_CREATE_ENTRIES  =
            "CREATE TABLE images ("
            + " id INTEGER AUTOINCREMENT PRIMARY KEY,"
            + " Path TEXT,"
            + " Comment TEXT"
            + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    //コンストラクタ
    DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //createScheduleTable(db);
        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // アップデートの判別
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

/*
    private void createScheduleTable(SQLiteDatabase db){
        // テーブル作成SQL
        String sql = "CREATE TABLE images ("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " Path TEXT,"
                + " Comment TEXT"
                + ");";
        db.execSQL(sql);
        Log.i(TAG, "テーブルimagesが作成されました");
    }*/
}
