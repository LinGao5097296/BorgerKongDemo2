package com.demo.borgerkongdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.demo.borgerkongdemo.util.LogUtils;

import java.util.List;

public class OrderDBManager {
    private Context context;
    private OrderDBOpenHelper databaseOpenHelper;
    private SQLiteDatabase dbReader;
    private SQLiteDatabase dbWriter;
    private static OrderDBManager instance;

    public OrderDBManager(Context context) {
        this.context = context;
        databaseOpenHelper = new OrderDBOpenHelper(context);
        //
        dbReader = databaseOpenHelper.getReadableDatabase();
        dbWriter = databaseOpenHelper.getWritableDatabase();
    }

    //getInstance
    public static synchronized OrderDBManager getInstance(Context context) {
        if (instance == null) {
            instance = new OrderDBManager(context);
        }
        return instance;
    }

    //
    public void addToDB(String bid,String title, String content, String time) {
        //
        ContentValues cv = new ContentValues();
        cv.put(OrderDBOpenHelper.BID, bid);
        cv.put(OrderDBOpenHelper.TITLE, title);
        cv.put(OrderDBOpenHelper.CONTENT, content);
        cv.put(OrderDBOpenHelper.TIME, time);
        Log.v(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception())+title);
        dbWriter.insert(OrderDBOpenHelper.TABLE_NAME, null, cv);
    }

    //
    public void readFromDB(List<Order> orderList) {
        Cursor cursor = dbReader.query(OrderDBOpenHelper.TABLE_NAME, null, null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                Order order = new Order();
                order.setId(cursor.getInt(cursor.getColumnIndex(OrderDBOpenHelper.ID)));
                order.setBid(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.BID)));
                order.setTitle(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.TITLE)));
                order.setContent(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.CONTENT)));
                order.setTime(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.TIME)));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //
    public void updateOrder(int noteID, String bid, String title, String content, String time) {
        ContentValues cv = new ContentValues();
        cv.put(OrderDBOpenHelper.ID, noteID);
        cv.put(OrderDBOpenHelper.BID, bid);
        cv.put(OrderDBOpenHelper.TITLE, title);
        cv.put(OrderDBOpenHelper.CONTENT, content);
        cv.put(OrderDBOpenHelper.TIME, time);
        dbWriter.update(OrderDBOpenHelper.TABLE_NAME, cv, "_id = ?", new String[]{noteID + ""});
    }

    //
    public void deleteNote(int noteID) {
        dbWriter.delete(OrderDBOpenHelper.TABLE_NAME, "_id = ?", new String[]{noteID + ""});
    }

    //
    public Order readData(int noteID) {
        Cursor cursor = dbReader.rawQuery("SELECT * FROM note WHERE _id = ?", new String[]{noteID + ""});
        cursor.moveToFirst();
        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndex(OrderDBOpenHelper.ID)));
        order.setBid(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.BID)));
        order.setTitle(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.TITLE)));
        order.setContent(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.CONTENT)));
        order.setTime(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.TIME)));
        return order;
    }
    public Order getOrderByMid(String bid) {
        Cursor cursor = dbReader.rawQuery("SELECT * FROM note WHERE bid = ?", new String[]{bid});
        cursor.moveToFirst();
        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndex(OrderDBOpenHelper.ID)));
        order.setBid(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.BID)));
        order.setTitle(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.TITLE)));
        order.setContent(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.CONTENT)));
        order.setTime(cursor.getString(cursor.getColumnIndex(OrderDBOpenHelper.TIME)));
        return order;
    }
}


