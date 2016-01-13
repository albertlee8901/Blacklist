package com.example.blacklist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Blacklist DB Helper
 * Created by albertli on 1/11/2016.
 */
public class BlacklistDBHelper extends SQLiteOpenHelper {
    public BlacklistDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "BlackDBHelper onCreate()");
        String createTable = "create table numbers (_id integer primary key autoincrement, number varchar)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TAG", "BlackDBHelper onUpgrade()");

    }
}
