package com.api.taskforge.controller;

import com.api.taskforge.model.Task;
import com.api.taskforge.model.User;
import com.api.taskforge.service.TaskService;
import com.api.taskforge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @PostMapping("/createTask/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable UUID userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(taskService.createTask(task, user));
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable UUID taskId) {
        Task completedTask = taskService.setTaskAsCompleted(taskId);
        if (completedTask != null) {
            return ResponseEntity.ok(completedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllTasksByUser/{userId}")
    public ResponseEntity<Page<Task>> getAllTasksByUser(@PathVariable UUID userId, Pageable pageable) throws BadRequestException {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Page<Task> tasks = taskService.getAllTasksByUser(user, pageable);
        if (tasks != null) {
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.ok(taskService.getAllTasksByUser(user, pageable));
    }

    @GetMapping("/getTask/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
