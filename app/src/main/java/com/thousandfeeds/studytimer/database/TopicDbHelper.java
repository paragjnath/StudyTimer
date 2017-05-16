package com.thousandfeeds.studytimer.database;

import android.content.Context;
import com.thousandfeeds.studytimer.database.TopicsContract.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class for creating updating and deleting the database.
 */

public class TopicDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    // Name of the database
    public static final String DATABASE_NAME = "StudyTimer.db";

    //Sql commend for creating all the four tables.
    private static final String CREATE_TABLE_TOPICS = "CREATE TABLE "+ TopicsTable.TABLE_NAME+" ( "
            +TopicsTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +TopicsTable.COLUMN_TOPIC_TITLE+" TEXT NOT NULL, "
            +TopicsTable.COLUMN_TOPIC_TIME_STAMP+" INTEGER, "
            +TopicsTable.COLUMN_TOPIC_COMPLETION_TIME+" INTEGER, "
            +TopicsTable.COLUMN_TOPIC_NO_OF_STEPS+" INTEGER, "
            +TopicsTable.COLUMN_TOPIC_STEPS_COMPLETED+" INTEGER, "
            +TopicsTable.COLUMN_TOPIC_NO_OF_NOTES+" INTEGER, "
            +TopicsTable.COLUMN_TOPIC_NO_OF_DOUBTS+" INTEGER, "
            +TopicsTable.COLUMN_TOPIC_STARRED_FLAG+" INTEGER );"
            ;

    private static final String CREATE_TABLE_STEPS = "CREATE TABLE "+StepsTable.STEPS_TABLE_NAME+" ( "
            +StepsTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +StepsTable.COLUMN_STEPS_TITLE+" TEXT NOT NULL, "
            +StepsTable.COLUMN_STEPS_TIME_STAMP+" INTEGER, "
            +StepsTable.COLUMN_STEPS_TIMER_DURATION+" INTEGER, "
            +StepsTable.COLUMN_STEPS_TIMER_PROGRESS+" INTEGER, "
            +StepsTable.COLUMN_STEPS_COMPLETION_TIME+" INTEGER, "
            +StepsTable.COLUMN_TOPIC_ID+" INTEGER, "
            +StepsTable.COLUMN_TOPIC_TITLE+" TEXT, FOREIGN KEY( "
            +StepsTable.COLUMN_TOPIC_ID+") REFERENCES "
            +TopicsTable.TABLE_NAME+"("+TopicsTable._ID+") ON DELETE NO ACTION ON UPDATE NO ACTION );"
            ;


    private static final String CREATE_TABLE_NOTES = "CREATE TABLE "+NotesTable.NOTES_TABLE_NAME+" ( "
            +NotesTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +NotesTable.COLUMN_NOTE_TITLE+" TEXT NOT NULL, "
            +NotesTable.COLUMN_NOTE_CONTENTS+" TEXT, "
            +NotesTable.COLUMN_NOTE_TIME_STAMP+" INTEGER, "
            +NotesTable.COLUMN_TOPIC_ID+" INTEGER, "
            +NotesTable.COLUMN_TOPIC_TITLE+" TEXT, "
            +NotesTable.COLUMN_NOTE_STARRED_FLAG+" INTEGER, FOREIGN KEY( "
            +NotesTable.COLUMN_TOPIC_ID+") REFERENCES "
            +TopicsTable.TABLE_NAME+"("+TopicsTable._ID+") ON DELETE NO ACTION ON UPDATE NO ACTION );"
            ;

    private static final String CREATE_TABLE_DOUBTS ="CREATE TABLE "+ DoubtsTable.DOUBTS_TABLE_NAME+" ( "
            +DoubtsTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +DoubtsTable.COLUMN_DOUBT_TITLE+" TEXT NOT NULL, "
            +DoubtsTable.COLUMN_DOUBT_TIME_STAMP+" INTEGER, "
            +DoubtsTable.COLUMN_TOPIC_ID+" INTEGER, "
            +DoubtsTable.COLUMN_TOPIC_TITLE+" TEXT, "
            +DoubtsTable.COLUMN_DOUBT_COMPLETION_FLAG+" INTEGER, "
            +DoubtsTable.COLUMN_DOUBT_COMPLETION_TIME+" INTEGER, FOREIGN KEY( "
            +DoubtsTable.COLUMN_TOPIC_ID+") REFERENCES "
            +TopicsTable.TABLE_NAME+"("+TopicsTable._ID+") ON DELETE NO ACTION ON UPDATE NO ACTION );"
            ;

    public TopicDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_TOPICS);
        sqLiteDatabase.execSQL(CREATE_TABLE_DOUBTS);
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTES);
        sqLiteDatabase.execSQL(CREATE_TABLE_STEPS);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
