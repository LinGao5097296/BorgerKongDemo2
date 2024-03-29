package com.demo.borgerkongdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDBOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "note";
    public static final int VERSION = 1;
    public static final String BID = "bid";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String TIME = "time";
    public static final String ID = "_id";

    public OrderDBOpenHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ("
                + ID + " integer primary key autoincrement  ,"
                + CONTENT + " TEXT,"
                + TITLE + " TEXT NOT NULL,"
                + BID + " integer NOT NULL,"
                + TIME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
