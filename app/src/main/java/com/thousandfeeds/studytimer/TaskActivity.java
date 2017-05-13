package com.thousandfeeds.studytimer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thousandfeeds.studytimer.database.TasksContract;
import com.thousandfeeds.studytimer.fragments.StepsFragment;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> , StepsFragment.OnListFragmentInteractionListener {

    Uri currentTaskUri;
    public static final int TASK_LOADER_ID =1;
    String currentTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Intent intent = getIntent();
        currentTaskUri = intent.getData();
        currentTaskId = currentTaskUri.getLastPathSegment();

        Button insertTodoButton = (Button) findViewById(R.id.insertToDoButton);
        insertTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTodo();
            }
        });

        // Kick off the loader
        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
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
            String  timerDuration = cursor.getString(timerDurationColumnIndex);
            long completionTime = cursor.getLong(completionTimeColumnIndex);
            long timeStamp = cursor.getLong(timeStampColumnIndex);

            //show data in view
            TextView titleTextView = (TextView) findViewById(R.id.taskTitleTextView);
            TextView taskDurationTextView = (TextView) findViewById(R.id.taskDurationTextView);

            titleTextView.setText(title);
            taskDurationTextView.setText(currentTaskId);


        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onListFragmentInteraction(Uri uri) {

    }

    private void insertTodo(){
        EditText todoTitleEditText = (EditText) findViewById(R.id.enterToDoEditText);
        String todoTitle = todoTitleEditText.getText().toString();

        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();

        ContentValues values = new ContentValues();
        values.put(TasksContract.ToDoListTable.COLUMN_TODO_TITLE, todoTitle);
        values.put(TasksContract.ToDoListTable.COLUMN_TODO_TASK_ID, currentTaskId);
        values.put(TasksContract.ToDoListTable.COLUMN_TODO_TIME_STAMP, time);

        Uri newUri = getContentResolver().insert(TasksContract.ToDoListTable.CONTENT_URI, values);

    }
}
