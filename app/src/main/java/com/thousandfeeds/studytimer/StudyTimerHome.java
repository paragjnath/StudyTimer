package com.thousandfeeds.studytimer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thousandfeeds.studytimer.database.TaskDbHelper;
import com.thousandfeeds.studytimer.database.TasksContract.*;

public class StudyTimerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_timer_home);

        //Added following lines to test the creation of the database at the first launch.
        //TaskDbHelper mTaskDbHelper = new TaskDbHelper(this);
        //SQLiteDatabase database = mTaskDbHelper.getReadableDatabase();

        String[] projection = {
                TasksTable._ID,
                TasksTable.COLUMN_TASK_TITLE,
                TasksTable.COLUMN_TASK_TIMER_DURATION ,
                TasksTable.COLUMN_TASK_COMPLETE_TIME ,
                TasksTable.COLUMN_TASK_TIME_STAMP ,
        };

        Cursor cursor = getContentResolver().query(TasksTable.CONTENT_URI,projection,null,null,null);
        Log.v("StudyTimerHome", cursor.toString());
    }
}
