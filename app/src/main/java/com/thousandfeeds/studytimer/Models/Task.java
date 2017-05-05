package com.thousandfeeds.studytimer.Models;


public class Task {

    String TaskId;
    String TaskTitle;
    String TimeDuration;
    String TaskCompletionTime;
    String TaskTimeStamp;

    public Task(String taskId, String taskTitle, String timeDuration, String taskCompletionTime, String taskTimeStamp) {
        TaskId = taskId;
        TaskTitle = taskTitle;
        TimeDuration = timeDuration;
        TaskCompletionTime = taskCompletionTime;
        TaskTimeStamp = taskTimeStamp;
    }

    public Task() {
        TaskId = "";
        TaskTitle = "";
        TimeDuration = "";
        TaskCompletionTime = "";
        TaskTimeStamp = "";
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public String getTimeDuration() {
        return TimeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        TimeDuration = timeDuration;
    }

    public String getTaskCompletionTime() {
        return TaskCompletionTime;
    }

    public void setTaskCompletionTime(String taskCompletionTime) {
        TaskCompletionTime = taskCompletionTime;
    }

    public String getTaskTimeStamp() {
        return TaskTimeStamp;
    }

    public void setTaskTimeStamp(String taskTimeStamp) {
        TaskTimeStamp = taskTimeStamp;
    }
}


