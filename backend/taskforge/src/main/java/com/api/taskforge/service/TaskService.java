package com.api.taskforge.service;

import com.api.taskforge.model.Task;
import com.api.taskforge.model.User;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {

    Task createTask(Task task, User user);

    Task updateTask(UUID taskId, Task taskDetails);

    void deleteTask(UUID taskId);

    Task setTaskAsCompleted(UUID taskId);

    Page<Task> getAllTasksByUser(User user, Pageable pageable) throws BadRequestException;

    Task getTaskById(UUID taskId);

    Page<Task> getAllTasks(Pageable pageable) throws BadRequestException;

}
