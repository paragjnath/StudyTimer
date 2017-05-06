package com.thousandfeeds.studytimer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thousandfeeds.studytimer.database.TasksContract;
import com.thousandfeeds.studytimer.fragments.TodoFragment;

public class TaskActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> , TodoFragment.OnListFragmentInteractionListener {

    Uri currentTaskUri;
    public static final int TASK_LOADER_ID =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Intent intent = getIntent();
        currentTaskUri = intent.getData();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                TasksContract.TasksTable._ID,
                TasksContract.TasksTable.COLUMN_TASK_TITLE,
                TasksContract.TasksTable.COLUMN_TASK_TIMER_DURATION ,
                TasksContract.TasksTable.COLUMN_TASK_COMPLETE_TIME ,
                TasksContract.TasksTable.COLUMN_TASK_TIME_STAMP ,
        };
        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                currentTaskUri,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if(cursor.moveToFirst()){

            // find the columns of the task attributes
            int titleColumnIndex = cursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TITLE);
            int timerDurationColumnIndex = cursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TITLE);
            int completionTimeColumnIndex = cursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TITLE);
            int timeStampColumnIndex = cursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TITLE);

            //get data from the columns
            String title = cursor.getString(titleColumnIndex);
            long timerDuration = cursor.getLong(timerDurationColumnIndex);
            long completionTime = cursor.getLong(completionTimeColumnIndex);
            long timeStamp = cursor.getLong(timeStampColumnIndex);


        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onListFragmentInteraction(Cursor cursor) {

    }
}
