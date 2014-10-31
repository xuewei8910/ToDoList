package com.xuewei8910.todolist;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Wei on 2014/10/29.
 */
public class ToDoTask {
    private String content;
    private Calendar deadline;



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

    public void setContent(String content){
        this.content = content;
    }

    public void setDeadline(Calendar calendar){
        deadline = calendar;
    }
}
