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
import android.util.Log;

import com.thousandfeeds.studytimer.database.TopicsContract.*;

//Class for inserting and accessing data from the database.

public class TopicProvider extends ContentProvider {

    /** URI matcher code for the content URI for the TOPICS table */
    private static final int TOPICS = 100;

    /** URI matcher code for the content URI for a single task in the TOPICS table */
    private static final int TOPIC_ID = 101;

    /** URI matcher code for the content URI for the todo list table */
    private static final int STEPS = 103;

    /** URI matcher code for the content URI for a single todo in the todo list table */
    private static final int STEP_ID = 104;

    /** URI matcher code for the content URI for the notes table */
    private static final int NOTES = 105;

    /** URI matcher code for the content URI for a single note in the notes table */
    private static final int NOTE_ID = 106;
    
    private static final int DOUBTS = 107;
    private static final int DOUBT_ID = 108;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    TopicDbHelper topicDbHelper;

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI that will help to access whole TOPICS table.
        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_TOPICS, TOPICS);
        // The content URI in the form /# , where # can be replaced by any integer so that we can access specific row matching the id given in the URI.
        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_TOPICS+"/#", TOPIC_ID);

        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_STEPS, STEPS);
        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_STEPS+"/#", STEP_ID);

        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_DOUBTS, DOUBTS);
        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_DOUBTS+"/#", DOUBT_ID);

        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_NOTES, NOTES);
        sUriMatcher.addURI(TopicsContract.CONTENT_AUTHORITY, TopicsContract.PATH_NOTES+"/#", NOTE_ID);
    }



    @Override
    public boolean onCreate() {

        topicDbHelper = new TopicDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //Get readable database
        SQLiteDatabase database = topicDbHelper.getReadableDatabase();

        //cursor to hold the result of the query
        Cursor cursor ;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match){

            case TOPICS:
                cursor = database.query(TopicsTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case TOPIC_ID:
                selection = TopicsTable._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(TopicsTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case DOUBTS:
                cursor = database.query(DoubtsTable.DOUBTS_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case DOUBT_ID:
                selection = DoubtsTable._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(DoubtsTable.DOUBTS_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case STEPS:
                cursor = database.query(StepsTable.STEPS_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case STEP_ID:
                selection = StepsTable._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(StepsTable.STEPS_TABLE_NAME, projection, selection, selectionArgs,
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

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TOPICS:
                return insertTopic(uri, contentValues);

            case STEPS:
                return insertStep(uri, contentValues);
            case DOUBTS:
                return insertDoubt(uri, contentValues);
            case NOTES:
                return insertNote(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private Uri insertTopic(Uri uri, ContentValues contentValues){

        //Get writable database
        SQLiteDatabase database = topicDbHelper.getWritableDatabase();

        long id = database.insert(TopicsTable.TABLE_NAME,null,contentValues);
        if (id == -1) {
            Log.e("TopicProvider", "Failed to insert row for " + uri);
            return null;
        }
        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertDoubt(Uri uri, ContentValues contentValues){

        //Get writable database
        SQLiteDatabase database = topicDbHelper.getWritableDatabase();

        long id = database.insert(DoubtsTable.DOUBTS_TABLE_NAME, null, contentValues);
        if (id == -1) {
            Log.e("TopicProvider", "Failed to insert row for " + uri);
            return null;
        }
        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertNote(Uri uri, ContentValues contentValues){

        //Get writable database
        SQLiteDatabase database = topicDbHelper.getWritableDatabase();

        long id = database.insert(NotesTable.NOTES_TABLE_NAME, null, contentValues);
        if (id == -1) {
            Log.e("TopicProvider", "Failed to insert row for " + uri);
            return null;
        }
        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertStep(Uri uri, ContentValues contentValues){

        //Get writable database
        SQLiteDatabase database = topicDbHelper.getWritableDatabase();

        long id = database.insert(StepsTable.STEPS_TABLE_NAME, null, contentValues);
        if (id == -1) {
            Log.e("TopicProvider", "Failed to insert row for " + uri);
            return null;
        }
        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

}
