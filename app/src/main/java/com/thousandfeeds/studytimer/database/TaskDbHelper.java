package com.thousandfeeds.studytimer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class for creating updating reading and deleting database and its data.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    // Name of the database
    public static final String DATABASE_NAME = "StudyTimer.db";

    //Sql commend for creating all the three tables.
    private static final String CREATE_TABLE_TASKS = "";
    private static final String CREATE_TABLE_TODO_LIST = "";
    private static final String CREATE_TABLE_NOTES = "";




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
