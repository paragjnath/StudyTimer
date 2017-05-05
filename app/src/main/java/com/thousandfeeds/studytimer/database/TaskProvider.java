package com.thousandfeeds.studytimer.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.thousandfeeds.studytimer.database.TasksContract.*;

//Class for inserting and accessing data from the database.

public class TaskProvider extends ContentProvider {

    /** URI matcher code for the content URI for the tasks table */
    private static final int TASKS = 100;

    /** URI matcher code for the content URI for a single task in the tasks table */
    private static final int TASK_ID = 101;

    /** URI matcher code for the content URI for the todo list table */
    private static final int TODO_LIST = 103;

    /** URI matcher code for the content URI for a single todo in the todo list table */
    private static final int TODO_ID = 104;

    /** URI matcher code for the content URI for the notes table */
    private static final int NOTES = 105;

    /** URI matcher code for the content URI for a single note in the notes table */
    private static final int NOTE_ID = 106;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    TaskDbHelper taskDbHelper;

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI that will help to access whole tasks table.
        sUriMatcher.addURI(TasksContract.CONTENT_AUTHORITY, TasksContract.PATH_TASKS, TASKS);
        // The content URI in the form /# , where # can be replaced by any integer so that we can access specific row matching the id given in the URI.
        sUriMatcher.addURI(TasksContract.CONTENT_AUTHORITY, TasksContract.PATH_TASKS+"/#", TASK_ID);

        sUriMatcher.addURI(TasksContract.CONTENT_AUTHORITY, TasksContract.PATH_TODO_LIST, TODO_LIST);
        sUriMatcher.addURI(TasksContract.CONTENT_AUTHORITY, TasksContract.PATH_TODO_LIST+"/#", TODO_ID);

        sUriMatcher.addURI(TasksContract.CONTENT_AUTHORITY, TasksContract.PATH_NOTES, NOTES);
        sUriMatcher.addURI(TasksContract.CONTENT_AUTHORITY, TasksContract.PATH_NOTES+"/#", NOTE_ID);
    }



    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //Get readable database
        SQLiteDatabase database = taskDbHelper.getReadableDatabase();

        //cursor to hold the result of the query
        Cursor cursor ;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match){
            case TASKS:

                cursor = database.query(TasksTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            case TASK_ID:

                selection = TasksTable._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(TasksTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            case TODO_LIST:

                cursor = database.query(ToDoListTable.TODO_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            case TODO_ID:

                selection = ToDoListTable._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(ToDoListTable.TODO_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            case NOTES:

                cursor = database.query(NotesTable.NOTES_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;

            case NOTE_ID:

                selection = NotesTable._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(NotesTable.NOTES_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
