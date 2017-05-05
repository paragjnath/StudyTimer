package com.thousandfeeds.studytimer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
}
