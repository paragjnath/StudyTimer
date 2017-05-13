package com.thousandfeeds.studytimer;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.thousandfeeds.studytimer.Models.Task;
import com.thousandfeeds.studytimer.adapters.TaskCursorAdapter;
import com.thousandfeeds.studytimer.database.DatabaseEditorHelper;
import com.thousandfeeds.studytimer.database.TasksContract;

public class StudyTimerHome extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int TASK_LOADER_ID =0;

    TaskCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_timer_home);

        ListView taskListView = (ListView) findViewById(R.id.taskList);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no task data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new TaskCursorAdapter(this, null);
        taskListView.setAdapter(mCursorAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(StudyTimerHome.this, TaskActivity.class);

                // Form the content URI that represents the specific task that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link TasksContract.TaskTable.CONTENT_URI}.

                Uri currentPetUri = ContentUris.withAppendedId(TasksContract.TasksTable.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentPetUri);

                // Launch the {@link EditorActivity} to display the data for the current pet.
                startActivity(intent);
            }
        });


        // Kick off the loader
        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);

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
                TasksContract.TasksTable.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        //update adapter when new data load finished

        mCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);

    }


}
