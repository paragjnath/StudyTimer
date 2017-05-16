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
import com.thousandfeeds.studytimer.database.TopicsContract;
import com.thousandfeeds.studytimer.fragments.StepsFragment;
import com.thousandfeeds.studytimer.fragments.TopicsFragment;

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
                TopicsContract.TopicsTable._ID,
                TopicsContract.TopicsTable.COLUMN_TOPIC_TITLE,
                TopicsContract.TopicsTable.COLUMN_TOPIC_TIME_STAMP,
                TopicsContract.TopicsTable.COLUMN_TOPIC_NO_OF_NOTES,
                TopicsContract.TopicsTable.COLUMN_TOPIC_NO_OF_DOUBTS,

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
            int titleColumnIndex = cursor.getColumnIndex(TopicsContract.TopicsTable.COLUMN_TOPIC_TITLE);
            int timeStampColumnIndex = cursor.getColumnIndex(TopicsContract.TopicsTable.COLUMN_TOPIC_TIME_STAMP);

            //get data from the columns
            String title = cursor.getString(titleColumnIndex);
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
        values.put(TopicsContract.DoubtsTable.COLUMN_DOUBT_TITLE, todoTitle);
        values.put(TopicsContract.DoubtsTable.COLUMN_TOPIC_ID, currentTaskId);
        values.put(TopicsContract.DoubtsTable.COLUMN_DOUBT_TIME_STAMP, time);

        Uri newUri = getContentResolver().insert(TopicsContract.DoubtsTable.CONTENT_URI, values);

    }
}
