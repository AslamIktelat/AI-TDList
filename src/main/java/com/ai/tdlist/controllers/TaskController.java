package com.ai.tdlist.controllers;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.exceptions.RepoCreateException;
import com.ai.tdlist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
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
    public boolean updateTask(@RequestBody Task task)
    {
        taskService.updateTask(task);
        return true;
    }
    @DeleteMapping("/task/delete/{key}")
    public boolean deleteTask(@PathVariable UUID key)
    {
        taskService.deleteTask(key);
        return true;
    }
    @GetMapping("/task/delete/{key}")
    public String getTask()
    {
        return "";
    }

}
