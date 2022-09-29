package com.example.tasktracker.service;

import com.example.tasktracker.dao.TaskDao;
import com.example.tasktracker.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    // in memory task DB data access layer
    private final TaskDao taskDao;

    @Autowired
    public TaskService(@Qualifier("postgres") TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // service to add a single task into the database
    public int addTask(Task task) {
        return taskDao.insertTask(task);
    }

    // service to retrieve all tasks in the database
    public List<Task> getAllTasks() {
        return taskDao.retrieveAllTasks();
    }

    // service to retrieve a task by its ID
    public Optional<Task> getTaskById(UUID taskId) {
        return taskDao.retrieveTaskById(taskId);
    }

    // service to update a task by its ID
    public int updateTaskById(UUID taskId, Task updatedTask) {
        return taskDao.updateTaskById(taskId, updatedTask);
    }

    // service to delete a task by its ID
    public int deleteTaskById(UUID taskId) {
        return taskDao.deleteTaskById(taskId);
    }
}
