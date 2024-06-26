package com.api.taskforge.service;

import com.api.taskforge.model.Task;
import com.api.taskforge.model.User;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Task createTask(Task task, User user);

    Task updateTask(Long taskId, Task taskDetails);

    void deleteTask(Long taskId);

    Task setTaskAsCompleted(Long taskId);

    Page<Task> getAllTasksByUser(User user, Pageable pageable) throws BadRequestException;

    Task getTaskById(Long taskId);

    Page<Task> getAllTasks(Pageable pageable) throws BadRequestException;

}
