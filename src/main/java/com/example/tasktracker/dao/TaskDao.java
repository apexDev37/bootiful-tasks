package com.example.tasktracker.dao;

import com.example.tasktracker.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskDao {

    // CRUD operations
    // Create operations
    int insertTask(UUID taskId, Task task);

    default int insertTask(Task task) {
        // default method to generate a random ID for each task

        UUID generatedId = UUID.randomUUID();
        return insertTask(generatedId, task);
    }

    // Retrieve operations
    List<Task> retrieveAllTasks();

    Optional<Task> retrieveTaskById(UUID taskId);

    // Update operations
    int updateTaskById(UUID taskId, Task updatedTask);

    // Delete operations
    int deleteTaskById(UUID taskId);

}
