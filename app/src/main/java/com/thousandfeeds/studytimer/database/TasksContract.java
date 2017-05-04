package com.thousandfeeds.studytimer.database;

import android.net.Uri;
import android.provider.BaseColumns;

//class containing the required constants for the database.

public final class TasksContract {

    public static final String CONTENT_AUTHORITY = "com.thousandfeeds.studytimer";

    /** we concatenate the CONTENT_AUTHORITY constant with the scheme
     * “content://” we will create the BASE_CONTENT_URI which will be shared by every URI associated with TasksContract */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TODO_LIST = "todolist";
    public static final String PATH_NOTES = "notes";
    public static final String PATH_TASKS = "tasks";

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TasksContract() {
    }


    // Constants for Main Task table.
    static class TasksTable implements BaseColumns {

        /** The content URI to access the Task data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TASKS);

        static final String TABLE_NAME = "Tasks";
        static final String COLUMN_TASK_TITLE = "Task-title";
        static final String COLUMN_TASK_TIMER_DURATION = "Timer-duration";
        static final String COLUMN_TASK_COMPLETE_TIME = "Task-completion-time";
        static final String COLUMN_TASK_TIME_STAMP = "Task-time-stamp";

        /*

         CREATE TABLE Task (
         TaskId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         Task-title TEXT NOT NULL,
         Timer-duration INTEGER,
         Task-completion-time INTEGER,
         Task-time-stamp INTEGER
         );

         */
    }

    // Constants for the Todo list table
    static class ToDoListTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODO_LIST);

        static final String TODO_TABLE_NAME = "Todo-list";
        static final String COLUMN_TODO_TITLE = "Todo-title";
        static final String COLUMN_TODO_TASK_ID = "Task-id";
        static final String COLUMN_TODO_TIME_STAMP = "Todo-time-stamp";

        /*
         CREATE TABLE Todo-list (
         TodoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         Todo-title TEXT NOT NULL,
         Task-id INTEGER,
         Todo-time-stamp INTEGER,
         FOREIGN KEY(TaskId) REFERENCES Task (TaskId) ON DELETE NO ACTION ON UPDATE NO ACTION
         );

         */

    }

    // Constants for the Notes table
    static class NotesTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        static final String NOTES_TABLE_NAME = "Notes";
        static final String COLUMN_NOTE_CONTENTS = "Note-content";
        static final String COLUMN_NOTE_TASK_ID = "Task-id";
        static final String COLUMN_NOTE_TIME_STAMP = "Note-time-stamp";

        /*
         CREATE TABLE Notes (
         NotesId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         Notes-contents TEXT NOT NULL,
         Task-id INTEGER,
         Notes-time-stamp INTEGER,
         FOREIGN KEY(TaskId) REFERENCES Task (TaskId) ON DELETE NO ACTION ON UPDATE NO ACTION
         );

         */
    }


}
