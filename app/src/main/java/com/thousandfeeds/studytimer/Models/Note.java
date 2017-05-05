package com.thousandfeeds.studytimer.Models;


public class Note {

    String NoteId;
    String NoteContent;
    String TaskId;
    String NoteTimeStamp;

    public Note(String noteId, String noteContent, String taskId, String noteTimeStamp) {
        NoteId = noteId;
        NoteContent = noteContent;
        TaskId = taskId;
        NoteTimeStamp = noteTimeStamp;
    }

    public void setNoteId(String noteId) {
        NoteId = noteId;
    }

    public void setNoteContent(String noteContent) {
        NoteContent = noteContent;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public void setNoteTimeStamp(String noteTimeStamp) {
        NoteTimeStamp = noteTimeStamp;
    }

    public String getNoteId() {
        return NoteId;
    }

    public String getNoteContent() {
        return NoteContent;
    }

    public String getTaskId() {
        return TaskId;
    }

    public String getNoteTimeStamp() {
        return NoteTimeStamp;
    }
}

