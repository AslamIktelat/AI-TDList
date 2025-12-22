package com.ai.tdlist.controllers;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.enums.TaskStatus;
import com.ai.tdlist.exceptions.RepoCreateException;
import com.ai.tdlist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/todo")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskService taskService;
    @PostMapping("/task/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws RepoCreateException
    {
        logger.info("Received request to create task: {}", task);
        Task createdTask = taskService.createTask(task);
        logger.info("Task created successfully: {}", createdTask);
        return ResponseEntity.ok().body(createdTask);
    }
    @PatchMapping("/task/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task)
    {
        logger.info("Received request to update task: {}", task);
         Task updatedTask= taskService.updateTask(task);
         return ResponseEntity.ok().body(updatedTask);

    }
    @DeleteMapping("/task/delete/{key}")
    public ResponseEntity<String> deleteTask(@PathVariable int key)
    {
        logger.info("Received request to delete task with key: {}", key);
        taskService.deleteTask(key);
        return ResponseEntity.ok().body("Task with key " + key + " deleted successfully.");
    }
    @GetMapping("/task/get")
    public ResponseEntity<List<Task>> getTask(@RequestParam(name = "status") TaskStatus status)
    {
        logger.info("Received request to get tasks with status: {}", status);
        List<Task> tasks= taskService.getTasksByStatus(status);
        return ResponseEntity.ok().body(tasks);
    }

}
