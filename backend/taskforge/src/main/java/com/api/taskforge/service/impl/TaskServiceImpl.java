package com.api.taskforge.service.impl;

import com.api.taskforge.model.Task;
import com.api.taskforge.model.User;
import com.api.taskforge.repository.TaskRepository;
import com.api.taskforge.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, User user) {
        if (task == null) {
            log.error("Task: {}, is null", (Object) null);
            return null;
        }
        if (user == null) {
            log.error("User: {}, is null", (Object) null);
            return null;
        }
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        if (taskId == null) {
            log.error("Task ID: {}, is null", (Object) null);
            return null;
        }
        if (task == null) {
            log.error("Task Details: {}, is null", (Object) null);
            return null;
        }
        Task newTask = taskRepository.findById(taskId).orElse(null);
        if (newTask == null) {
            log.error("Task {} with ID {} is null", taskId, task);
            return null;
        }
        if (task.getCreatedAt()!= null) {
            newTask.setCreatedAt(task.getCreatedAt());
        }
        if (task.getDescription()!= null) {
            newTask.setDescription(task.getDescription());
        }
        if (task.getDueDate()!= null) {
            newTask.setDueDate(task.getDueDate());
        }
        if (task.getPriority()!= null) {
            newTask.setPriority(task.getPriority());
        }
        if (task.getTitle()!= null) {
            newTask.setTitle(task.getTitle());
        }
        if (task.getUser()!= null) {
            newTask.setUser(task.getUser());
        }
        newTask.setCompleted(task.getCompleted());
        return newTask;
    }

    @Override
    public void deleteTask(Long taskId) {
        if (taskId == null) {
            log.error("Task with ID: {}, is null and cannot be deleted", (Object) null);
            return;
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task setTaskAsCompleted(Long taskId) {
        if (taskId == null) {
            log.error("Task with ID: {}, is null", (Object) null);
            return null;
        }
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setCompleted(true);
        }
        return task;
    }

    @Override
    public Page<Task> getAllTasksByUser(User user, Pageable pageable) throws BadRequestException {
        if (user == null) {
            log.error("User {} is null", (Object) null);
            return null;
        }
        if (validatePageable(pageable)) {
            return taskRepository.findAllByUser(user, pageable);
        }
        throw new BadRequestException("Invalid Pageable");
    }

    private boolean validatePageable(Pageable pageable) {
        return pageable.getPageSize() >= 0 && pageable.getOffset() > 0;
    }

    @Override
    public Task getTaskById(Long taskId) {
        if (taskId == null) {
            log.error("Task ID {} is null", (Object) null);
            return null;
        }
        return taskRepository.findById(taskId).orElse(null);
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) throws BadRequestException {
        if (validatePageable(pageable)) {
            return taskRepository.findAll(pageable);
        }
        throw new BadRequestException("Invalid Pageable");
    }
}