package com.thousandfeeds.studytimer.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.thousandfeeds.studytimer.Models.Task;
import com.thousandfeeds.studytimer.database.TasksContract.*;

import java.util.Calendar;

public class DatabaseEditorHelper {
    private DatabaseEditorHelper() {
    }

    public static Task insertTask(Task task, ContentResolver contentResolver){

        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();

        ContentValues values = new ContentValues();
        values.put(TasksTable.COLUMN_TASK_TITLE, task.getTaskTitle());
        values.put(TasksTable.COLUMN_TASK_TIMER_DURATION, task.getTaskTitle());
        values.put(TasksTable.COLUMN_TASK_COMPLETE_TIME, task.getTaskCompletionTime());
        values.put(TasksTable.COLUMN_TASK_TIME_STAMP, time);

        Uri newUri = contentResolver.insert(TasksTable.CONTENT_URI, values);
        task.setTaskId(newUri.getLastPathSegment());
        return task;
    }

    public static Cursor getTasks(ContentResolver contentResolver){

        String[] projection = {
                TasksTable._ID,
                TasksTable.COLUMN_TASK_TITLE,
                TasksTable.COLUMN_TASK_TIMER_DURATION ,
                TasksTable.COLUMN_TASK_COMPLETE_TIME ,
                TasksTable.COLUMN_TASK_TIME_STAMP ,
        };

        Cursor cursor = contentResolver.query(TasksTable.CONTENT_URI,projection,null,null,null);
        Log.v("StudyTimerHome", cursor.toString());

        return cursor;
    }
}
