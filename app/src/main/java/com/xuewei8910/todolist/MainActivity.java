package com.xuewei8910.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends Activity {

    private ArrayList<ToDoTask> tasks;
    private TaskListAdapter taskListAdapter;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = new ArrayList<ToDoTask>();
        taskListAdapter = new TaskListAdapter(this, tasks);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(taskListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == tasks.size() - 1) {
                    final Calendar calendar = new GregorianCalendar();
                    final ToDoTask task = new ToDoTask("",calendar);

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Content");
                    final EditText input = new EditText(context);
                    builder.setView(input);
                    builder.setPositiveButton("Set Deadline",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            task.setContent(input.getText().toString());

                            new DatePickerDialog(context,
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                            calendar.set(Calendar.YEAR, year);
                                            calendar.set(Calendar.MONTH, monthOfYear);
                                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                            new TimePickerDialog(context,
                                                    new TimePickerDialog.OnTimeSetListener() {
                                                        @Override
                                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                                            calendar.set(Calendar.MINUTE, minute);

                                                            tasks.add(0, task);
                                                            taskListAdapter.notifyDataSetChanged();
                                                        }
                                                    }, calendar.get(Calendar.HOUR_OF_DAY),
                                                    calendar.get(Calendar.MINUTE), true).show();
                                        }
                                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)).show();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();


                } else {
                    tasks.remove(position);
                    taskListAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addTask(ToDoTask task) {
        tasks.add(task);
        taskListAdapter.notifyDataSetChanged();
    }
}
