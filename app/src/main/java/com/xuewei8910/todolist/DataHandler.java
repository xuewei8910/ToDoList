package com.xuewei8910.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wei on 2014/10/30.
 */
public class DataHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ToDoList.db";
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DEADLINE = "deadline";

    public DataHandler(Context context){
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
        + TABLE_NAME
        + "("
        + COLUMN_ID + " integer primary key autoincrement,"
        + COLUMN_TASK + " text not null,"
        + COLUMN_DEADLINE + " text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }
}
