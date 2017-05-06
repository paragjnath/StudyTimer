package com.thousandfeeds.studytimer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.thousandfeeds.studytimer.R;
import com.thousandfeeds.studytimer.database.TasksContract;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;


public class TaskCursorAdapter extends CursorAdapter {
    /**
     * Constructs a new {@link TaskCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public TaskCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false);
    }

    /**
     * This method binds the task data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        TextView textViewId = (TextView) view.findViewById(R.id.textViewId);

        // Find the columns of task attributes that we're interested in
        int titleColumnIndex = cursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TITLE);
        int idColumnIndex = cursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TIME_STAMP);

        // Read the task attributes from the Cursor for the current task
        String taskTitle = cursor.getString(titleColumnIndex);
        String taskId = cursor.getString(idColumnIndex);

        taskId = DateFormat.format("dd/MM/yyyy hh:mm:ss", Long.parseLong(taskId)).toString();

        // Update the TextViews with the attributes for the current task
        textViewTitle.setText(taskTitle);
        textViewId.setText(taskId);
    }
}
