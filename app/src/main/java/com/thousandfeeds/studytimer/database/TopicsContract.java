package com.thousandfeeds.studytimer.database;

import android.net.Uri;
import android.provider.BaseColumns;

//class containing the required constants for the database.

public final class TopicsContract {

    public static final String CONTENT_AUTHORITY = "com.thousandfeeds.studytimer";

    /** we concatenate the CONTENT_AUTHORITY constant with the scheme
     * “content://” we will create the BASE_CONTENT_URI which will be shared by every URI associated with TopicsContract */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DOUBTS = "Doubts";
    public static final String PATH_NOTES = "notes";
    public static final String PATH_TOPICS = "Topics";
    public static final String PATH_STEPS = "Steps";

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TopicsContract() {
    }


    // Constants for Main Topic table.
    public static class TopicsTable implements BaseColumns {

        /** The content URI to access the Topic data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TOPICS);

        public static final String TABLE_NAME = "Topics";
        public static final String COLUMN_TOPIC_TITLE = "TopicTitle";
        public static final String COLUMN_TOPIC_TIME_STAMP = "TopicTimeStamp";
        public static final String COLUMN_TOPIC_COMPLETION_TIME = "TopicCompletionTime";
        public static final String COLUMN_TOPIC_NO_OF_STEPS = "TopicNoOfSteps";
        public static final String COLUMN_TOPIC_STEPS_COMPLETED = "TopicStepsCompleted";
        public static final String COLUMN_TOPIC_NO_OF_NOTES = "TopicNoOfNotes";
        public static final String COLUMN_TOPIC_NO_OF_DOUBTS = "TopicNoOfDoubts";
        public static final String COLUMN_TOPIC_STARRED_FLAG = "TopicStarredFlag";

        /*
         CREATE TABLE Topic (
         TopicId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
         TopicTitle TEXT NOT NULL,
         TopicTimeStamp INTEGER,
         TopicCompletionTime INTEGER,
         TopicNoOfSteps INTEGER,
         TopicStepsCompleted INTEGER,
         TopicNoOfNotes INTEGER,
         TopicNoOfDoubts INTEGER,
         TopicStarredFlag INTEGER
         );
         */

    }


    public static final class StepsTable implements BaseColumns{


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STEPS);

        public static final String STEPS_TABLE_NAME = "Steps";
        public static final String COLUMN_STEPS_TITLE = "StepsTitle";
        public static final String COLUMN_STEPS_TIME_STAMP = "StepsTimeStamp";
        public static final String COLUMN_STEPS_TIMER_DURATION = "StepsTimerDuration";
        public static final String COLUMN_STEPS_TIMER_PROGRESS = "StepsTimerProgress";
        public static final String COLUMN_STEPS_COMPLETION_TIME = "SteplsCompletionTime";
        public static final String COLUMN_TOPIC_ID = "TopicId";
        public static final String COLUMN_TOPIC_TITLE = "TopicTitle";


    }

    // Constants for the Todo list table
    public static class DoubtsTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DOUBTS);

        public static final String DOUBTS_TABLE_NAME = "Doubts";
        public static final String COLUMN_DOUBT_TITLE = "DoubtTitle";
        public static final String COLUMN_DOUBT_TIME_STAMP = "DoubtTimeStamp";
        public static final String COLUMN_TOPIC_ID = "TopicId";
        public static final String COLUMN_TOPIC_TITLE = "TopicTitle";
        public static final String COLUMN_DOUBT_COMPLETION_FLAG = "DoubtCompletionFlag";
        public static final String COLUMN_DOUBT_COMPLETION_TIME = "DoubtCompletionTime";

    }

    // Constants for the Notes table
    public static class NotesTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        public static final String NOTES_TABLE_NAME = "Notes";
        public static final String COLUMN_NOTE_TITLE = "NoteTitle";
        public static final String COLUMN_NOTE_CONTENTS = "NoteContent";
        public static final String COLUMN_NOTE_TIME_STAMP = "NoteTimeStamp";
        public static final String COLUMN_TOPIC_ID = "TaskId";
        public static final String COLUMN_TOPIC_TITLE = "NoteTopicTitle";
        public static final String COLUMN_NOTE_STARRED_FLAG = "NoteStarredFlag";

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
