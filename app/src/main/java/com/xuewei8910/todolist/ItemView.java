package com.xuewei8910.todolist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;


/**
 * Created by Wei on 2014/10/29.
 */
public class ItemView extends RelativeLayout {

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_view,this,true);

    }

    public void setTask(ToDoTask task){
        final TextView content = (TextView)findViewById(R.id.taskContent);
        final TextView date = (TextView)findViewById(R.id.taskDeadline);

        content.setText(task.getContent());
        date.setText(task.getDeadline() == null ? "" : "Deadline: " +
                new SimpleDateFormat("EEE, MMM dd HH:mm").format(task.getDeadline().getTime()));
    }
}
