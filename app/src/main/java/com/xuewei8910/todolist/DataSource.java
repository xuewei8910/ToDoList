package com.xuewei8910.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Wei on 2014/10/30.
 */
public class DataSource {
    private DataHandler dataHandler;
    private ArrayList<ToDoTask> tasks;
    private String[] allColumns = {DataHandler.COLUMN_ID, DataHandler.COLUMN_TASK,
            DataHandler.COLUMN_DEADLINE};

    public DataSource(Context context){
        dataHandler = new DataHandler(context);
        tasks = new ArrayList<ToDoTask>();
    }

    protected void deleteTaskFromDB(ToDoTask task){
        SQLiteDatabase db = dataHandler.getWritableDatabase();
        long id = task.getId();
        db.delete(DataHandler.TABLE_NAME, DataHandler.COLUMN_ID + " = " + id, null);
    }

    protected long createTaskFromDB(ToDoTask task){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = dataHandler.getWritableDatabase();
        contentValues.put(DataHandler.COLUMN_TASK, task.getContent());
        contentValues.put(DataHandler.COLUMN_DEADLINE, task.getDeadlineString());
        return db.insert(DataHandler.TABLE_NAME, null, contentValues);
    }

    protected void updateTaskFromDB(ToDoTask task){
        SQLiteDatabase db = dataHandler.getWritableDatabase();
        db.execSQL("UPDATE "+DataHandler.TABLE_NAME
        + "SET " + DataHandler.COLUMN_TASK + " = " +task.getContent() + ", "
        + DataHandler.COLUMN_DEADLINE + " = " + task.getDeadlineString()
        + " WHERE " + DataHandler.COLUMN_ID + " = " +task.getId() + ";");
    }

    private ArrayList<ToDoTask> getAllTaskFromDB(){
        ArrayList<ToDoTask> allTask = new ArrayList<ToDoTask>();
        SQLiteDatabase db = dataHandler.getReadableDatabase();
        Cursor cursor = db.query(DataHandler.TABLE_NAME, allColumns, null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            allTask.add(cursorToTask(cursor));
            cursor.moveToNext();
        }

        return allTask;
    }

    private ToDoTask cursorToTask(Cursor cursor){
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(ToDoTask.dateFormat.parse(cursor.getString(2)));
            ToDoTask task = new ToDoTask(cursor.getString(1),calendar);
            task.setId(cursor.getLong(0));
            return task;
        }catch (Exception e){
            Log.i("DataSource", e.toString());
            return null;
        }
    }

    public ArrayList<ToDoTask> getTasks(){
        tasks.addAll(0,getAllTaskFromDB());
        return tasks;
    }

    public void addTask(ToDoTask task){
        tasks.add(0,task);
        task.setId(createTaskFromDB(task));
    }

    public void updateTask(ToDoTask task){
        updateTaskFromDB(task);
    }

    public void deleteTask(ToDoTask task){
        tasks.remove(task);
        deleteTaskFromDB(task);
    }
}
