package com.xuewei8910.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Wei on 2014/10/29.
 */
public class TaskListAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ToDoTask> tasks;

    public TaskListAdapter(Context context, ArrayList<ToDoTask> tasks){
        super();
        this.tasks = tasks;
        this.context = context;

        tasks.add(new ToDoTask("Add Task ...", null));
    }
    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks == null? null:tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private String content;
    private Calendar calendar;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (tasks != null && context != null){
            View itemView;
            itemView = new ItemView(context);
            ((ItemView)itemView).setTask(tasks.get(position));
            return itemView;
        }
        return null;
    }
}
