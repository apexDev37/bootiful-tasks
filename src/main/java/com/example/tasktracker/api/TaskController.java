 package com.example.tasktracker.api;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public void addTask(@RequestBody @Valid @NotNull Task task) {
        taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(path = "{taskId}")
    public Task getTaskById(@PathVariable("taskId") UUID taskId) {
        return taskService.getTaskById(taskId)
                .orElse(null);
    }

    @PutMapping(path = "{taskId}")
    public int updateTaskById(@PathVariable("taskId") UUID taskId,
                              @RequestBody @Valid @NotNull Task updatedTask) {
        return taskService.updateTaskById(taskId, updatedTask);
    }

    @DeleteMapping(path = "{taskId}")
    public int deleteTaskById(@PathVariable UUID taskId) {
        return taskService.deleteTaskById(taskId);
    }
}
