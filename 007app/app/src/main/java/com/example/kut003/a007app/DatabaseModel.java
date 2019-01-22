package com.example.kut003.a007app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseModel {

    DatabaseOpenHelper helper;
    SQLiteDatabase db;
    final String TABLE_NAME = DatabaseOpenHelper.TABLE_NAME;

    DatabaseModel(Context context){
        if(helper == null || db == null) {
            helper = new DatabaseOpenHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    // TODO:select文にwhereが無いため設計が必要
    public String searchData(){
        Cursor cursor = null;
        try{
            String sql    = "SELECT * FROM " + TABLE_NAME;
            cursor = db.rawQuery(sql , new String[]{}); //SQLの実行
            return readCursor(cursor);
        }
        finally{
            if( cursor != null ){
                cursor.close();
            }
        }
    }

    private String readCursor(Cursor cursor){
        try{
            //カーソル開始位置を先頭にする
            cursor.moveToFirst();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= cursor.getCount(); i++) {
                //SQL文の結果から、必要な値を取り出す
                sb.append(cursor.getString(1));
                cursor.moveToNext();
            }
            return sb.toString();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void insertData(String path, String comment){
        String sql = "insert into " + TABLE_NAME + " (path, comment) values(?,?);";
        String[] bindStr = new String[]{
                path,
                comment
        };
        try {
            db.execSQL(sql, bindStr);
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }
    }

    /*
    public void updateData(String path, String comment){
        String sql = "update " + TABLE_NAME
                + " SET Path =?, Comment = ? ;";
        String[] bindStr = new String[]{
                path,
                comment
        };
        try {
            db.execSQL(sql,bindStr);
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }
    }
    public void deleteData(String path){
        String sql = "DELETE FROM " + TABLE_NAME
                + " WHERE Path = ?;";
        String[] bindStr = new String[]{
                path
        };
        try {
            db.execSQL(sql,bindStr);
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }
    }
    */
}