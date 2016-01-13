package com.example.blacklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO
 * Created by albertli on 1/11/2016.
 */
public class BlacklistNumberDAO {
    private BlacklistDBHelper dbHelper;

    public BlacklistNumberDAO(Context context) {
        dbHelper = new BlacklistDBHelper(context, "Blacklist.db", null, 1);
    }

    public void add(BlacklistNumber blacklistNumber){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("number", blacklistNumber.getNumber());
        long insertId = db.insert("numbers",null,contentValues);
        Log.i("TAG","BlacklistNumberDAO add() id=" + insertId);

        db.close();

    }

    public void delete(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int deleteId = db.delete("numbers", "_id=?", new String[]{id + ""});
        Log.i("TAG","BlacklistNumberDAO delete() id=" + deleteId);

        db.close();

    }

    public void update(BlacklistNumber blacklistNumber){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("number", blacklistNumber.getNumber());
        int updateId = db.update("numbers", contentValues, "_id=?", new String[]{blacklistNumber.get_id() + ""});

        Log.i("TAG", "BlacklistNumberDAO update() id=" + updateId);

        db.close();

    }

    public List<BlacklistNumber> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<BlacklistNumber> blacklistNumbers = new ArrayList<BlacklistNumber>();

        Cursor cursor = db.query("numbers", null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String number = cursor.getString(1);
            blacklistNumbers.add(new BlacklistNumber(id, number));
        }

        cursor.close();
        db.close();

        return blacklistNumbers;

    }
}
