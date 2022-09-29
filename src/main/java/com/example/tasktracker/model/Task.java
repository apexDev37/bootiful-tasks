package com.example.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import javax.validation.constraints.NotBlank;

public class Task {

    // class members
    private final UUID taskId;
    @NotBlank
    private final String taskTitle;
    @NotBlank
    private final String taskDueDateAndTime;
    private final boolean taskReminderOn;


    public Task(
            @JsonProperty("taskId") UUID taskId,
            @JsonProperty("taskTitle") String taskTitle,
            @JsonProperty("taskDueDateAndTime") String taskDueDateAndTime,
            @JsonProperty("taskReminderOn") boolean taskReminderOn) {

        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDueDateAndTime = taskDueDateAndTime;
        this.taskReminderOn = taskReminderOn;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDueDateAndTime() {
        return taskDueDateAndTime;
    }

    public boolean isTaskReminderOn() {
        return taskReminderOn;
    }
}
