package com.thousandfeeds.studytimer.Models;


public class Todo {

    String TodoId;
    String TodoTitle;
    String TaskId;
    String TodoTimeStamp;

    public Todo(String todoId, String todoTitle, String taskId, String todoTimeStamp) {
        TodoId = todoId;
        TodoTitle = todoTitle;
        TaskId = taskId;
        TodoTimeStamp = todoTimeStamp;
    }

    public String getTodoId() {
        return TodoId;
    }

    public void setTodoId(String todoId) {
        TodoId = todoId;
    }

    public String getTodoTitle() {
        return TodoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        TodoTitle = todoTitle;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getTodoTimeStamp() {
        return TodoTimeStamp;
    }

    public void setTodoTimeStamp(String todoTimeStamp) {
        TodoTimeStamp = todoTimeStamp;
    }
}
