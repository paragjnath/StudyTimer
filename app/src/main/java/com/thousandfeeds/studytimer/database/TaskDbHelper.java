package com.thousandfeeds.studytimer.database;

import android.content.Context;
import com.thousandfeeds.studytimer.database.TasksContract.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class for creating updating and deleting the database.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    // Name of the database
    public static final String DATABASE_NAME = "StudyTimer.db";

    //Sql commend for creating all the three tables.
    private static final String CREATE_TABLE_TASKS = "CREATE TABLE "+ TasksTable.TABLE_NAME+" ( "
            +TasksTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +TasksTable.COLUMN_TASK_TITLE+" TEXT NOT NULL, "
            +TasksTable.COLUMN_TASK_TIMER_DURATION+" INTEGER, "
            +TasksTable.COLUMN_TASK_COMPLETE_TIME+" INTEGER, "
            +TasksTable.COLUMN_TASK_TIME_STAMP+" INTEGER );"
            ;

    private static final String CREATE_TABLE_TODO_LIST ="CREATE TABLE "+ ToDoListTable.TODO_TABLE_NAME+" ( "
            +ToDoListTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +ToDoListTable.COLUMN_TODO_TITLE+" TEXT NOT NULL, "
            +ToDoListTable.COLUMN_TODO_TASK_ID+" INTEGER, "
            +ToDoListTable.COLUMN_TODO_TIME_STAMP+" INTEGER, FOREIGN KEY( "
            +ToDoListTable.COLUMN_TODO_TASK_ID+") REFERENCES "
            +TasksTable.TABLE_NAME+"("+TasksTable._ID+") ON DELETE NO ACTION ON UPDATE NO ACTION );"
            ;

    private static final String CREATE_TABLE_NOTES = "CREATE TABLE "+NotesTable.NOTES_TABLE_NAME+" ( "
            +NotesTable._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            +NotesTable.COLUMN_NOTE_CONTENTS+" TEXT NOT NULL, "
            +NotesTable.COLUMN_NOTE_TASK_ID+" INTEGER, "
            +NotesTable.COLUMN_NOTE_TIME_STAMP+" INTEGER, FOREIGN KEY( "
            +NotesTable.COLUMN_NOTE_TASK_ID+") REFERENCES "
            +TasksTable.TABLE_NAME+"("+TasksTable._ID+") ON DELETE NO ACTION ON UPDATE NO ACTION );"
            ;

    public TaskDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_TASKS);
        sqLiteDatabase.execSQL(CREATE_TABLE_TODO_LIST);
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTES);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
