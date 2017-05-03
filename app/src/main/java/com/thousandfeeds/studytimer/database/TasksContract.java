package com.thousandfeeds.studytimer.database;

import android.provider.BaseColumns;

//class containing the required constants for the database.

public final class TasksContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TasksContract() {
    }

    // Constants for Main Task table.
    static class TaskTable implements BaseColumns {

        static final String TABLE_NAME = "Task";
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

        static final String NOTES_TABLE_NAME = "Notes";
        static final String COLUMN_NOTES_CONTENTS = "Notes-contents";
        static final String COLUMN_NOTES_TASK_ID = "Task-id";
        static final String COLUMN_NOTES_TIME_STAMP = "Notes-time-stamp";

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
