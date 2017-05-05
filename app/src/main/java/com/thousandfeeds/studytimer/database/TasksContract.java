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
    public static class TasksTable implements BaseColumns {

        /** The content URI to access the Task data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TASKS);

        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_TASK_TITLE = "TaskTitle";
        public static final String COLUMN_TASK_TIMER_DURATION = "TimeDuration";
        public static final String COLUMN_TASK_COMPLETE_TIME = "TaskCompletionTime";
        public static final String COLUMN_TASK_TIME_STAMP = "TaskTimeStamp";

        /*

         CREATE TABLE Task (
         TaskId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         TaskTitle TEXT NOT NULL,
         TimerDuration INTEGER,
         TaskCompletionTime INTEGER,
         TaskTimeStamp INTEGER
         );

         */
    }

    // Constants for the Todo list table
    public static class ToDoListTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODO_LIST);

        public static final String TODO_TABLE_NAME = "TodoList";
        public static final String COLUMN_TODO_TITLE = "TodoTitle";
        public static final String COLUMN_TODO_TASK_ID = "TaskId";
        public static final String COLUMN_TODO_TIME_STAMP = "TodoTimeStamp";

        /*
         CREATE TABLE Todo-list (
         TodoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         TodoTitle TEXT NOT NULL,
         TaskId INTEGER,
         TodoTimeStamp INTEGER,
         FOREIGN KEY(TaskId) REFERENCES Task (TaskId) ON DELETE NO ACTION ON UPDATE NO ACTION
         );

         */

    }

    // Constants for the Notes table
    public static class NotesTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        public static final String NOTES_TABLE_NAME = "Notes";
        public static final String COLUMN_NOTE_CONTENTS = "NoteContent";
        public static final String COLUMN_NOTE_TASK_ID = "TaskId";
        public static final String COLUMN_NOTE_TIME_STAMP = "NoteTimeStamp";

        /*
         CREATE TABLE Notes (
         NotesId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         NotesContents TEXT NOT NULL,
         TaskId INTEGER,
         NotesTimeStamp INTEGER,
         FOREIGN KEY(TaskId) REFERENCES Task (TaskId) ON DELETE NO ACTION ON UPDATE NO ACTION
         );

         */
    }


}
