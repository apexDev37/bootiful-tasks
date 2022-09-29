package com.example.tasktracker.dao;

import com.example.tasktracker.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class TaskDataAccessService implements TaskDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // reusable task row mapper instance
    RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        UUID taskId = UUID.fromString(rs.getString(1));
        String taskTitle = rs.getString(2);
        String taskDueDateAndTime = rs.getString(3);
        boolean taskReminderOn = rs.getBoolean(4);
        return new Task(
                taskId,
                taskTitle,
                taskDueDateAndTime,
                taskReminderOn
        );
    };

    @Override
    public int insertTask(UUID taskId, Task task) {
        final String sql =
                "INSERT INTO task" +
                "(task_id, " +
                "task_title, " +
                "task_due_date_time, " +
                "task_reminder_on) " +
                "VALUES " +
                "(" +
                "?, " +
                "?, " +
                "?, " +
                "? " +
                ")";

        return jdbcTemplate.update(
                        sql,
                        taskId,
                        task.getTaskTitle(),
                        task.getTaskDueDateAndTime(),
                        task.isTaskReminderOn()
        );
    }

    @Override
    public List<Task> retrieveAllTasks() {
        final String sql =
                "SELECT " +
                "task_id, " +
                "task_title, " +
                "task_due_date_time, " +
                "task_reminder_on " +
                "FROM task";

        return jdbcTemplate.query(sql, taskRowMapper);
    }

    @Override
    public Optional<Task> retrieveTaskById(UUID taskId) {
        final String sql =
                        "SELECT " +
                        "task_id, " +
                        "task_title, " +
                        "task_due_date_time, " +
                        "task_reminder_on " +
                        "FROM task " +
                        "WHERE task_id = ?";

        return jdbcTemplate.query(sql, taskRowMapper, taskId)
                .stream()
                .findFirst();
    }

    @Override
    public int updateTaskById(UUID taskId, Task updatedTask) {
        final String sql =
                "UPDATE task " +
                "SET " +
                "task_id = ?, " +
                "task_title = ?, " +
                "task_due_date_time = ?, " +
                "task_reminder_on = ? " +
                "WHERE task_id = ?";

        return jdbcTemplate.update(
                sql,
                taskId,
                updatedTask.getTaskTitle(),
                updatedTask.getTaskDueDateAndTime(),
                updatedTask.isTaskReminderOn(),
                taskId
        );
    }

    @Override
    public int deleteTaskById(UUID taskId) {
        final String sql =
                "DELETE " +
                "FROM task " +
                "WHERE task_id = ?";

        return jdbcTemplate.update(sql, taskId);
    }
}
