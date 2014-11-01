package com.xuewei8910.todolist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Wei on 2014/10/29.
 */
public class ToDoTask {
    private String content;
    private Calendar deadline;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd HH:mm");
    private long id;



    public ToDoTask(String content, Calendar deadline){
        this.content = content;
        this.deadline = deadline;
    }

    public String getContent(){
        return content;
    }

    public Calendar getDeadline(){
        return deadline;
    }

    public String getDeadlineString(){
        return dateFormat.format(deadline.getTime());
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setDeadline(Calendar calendar){
        deadline = calendar;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }
}
