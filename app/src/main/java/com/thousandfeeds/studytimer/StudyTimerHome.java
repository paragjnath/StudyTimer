package com.thousandfeeds.studytimer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thousandfeeds.studytimer.Models.Task;
import com.thousandfeeds.studytimer.database.DatabaseEditorHelper;

public class StudyTimerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_timer_home);

        //Added following lines to test the creation of the database at the first launch.
        //TaskDbHelper mTaskDbHelper = new TaskDbHelper(this);
        //SQLiteDatabase database = mTaskDbHelper.getReadableDatabase();

        Cursor cursor = DatabaseEditorHelper.getTasks(getContentResolver());

        Log.v("StudyTimerHome", cursor.toString());
    }

    public void save(View view) {

        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        EditText editTextDuration = (EditText) findViewById(R.id.editTextDuration);
        EditText editTextCompletionTime = (EditText) findViewById(R.id.editTextCompletionTime);
        EditText editTextTimeStamp = (EditText) findViewById(R.id.editTextTimeStamp);
        Task task = new Task();
        task.setTaskTitle(editTextTitle.getText().toString());
        task.setTaskCompletionTime(editTextCompletionTime.getText().toString());
        task.setTimeDuration(editTextDuration.getText().toString());
        task.setTaskTimeStamp(editTextTimeStamp.getText().toString());
        task = DatabaseEditorHelper.insertTask(task,getContentResolver());
        Log.v("main",task.getTaskId());
        TextView textViewId = (TextView) findViewById(R.id.textViewId);
        textViewId.setText("Task saved to database. Id is "+task.getTaskId());





    }
}
