package com.ai.tdlist.services;

import com.ai.tdlist.constants.Oprations;
import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.enums.TaskStatus;
import com.ai.tdlist.exceptions.RepoCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    RepoService repoService;

    public Task createTask(Task task)
    {
        try {
            validateTask(task);
            task.setStatus(TaskStatus.TODO);
            logger.info("Creating task in DB: {}", task);
            return repoService.createTask(task);
        }
          catch (Exception e)
        {
            logger.error("Error occurred while creating task: {}", e.toString());
            throw new RepoCreateException(e.toString());
        }

    }
    public boolean updateTask(Task task)
    {
        return true;
    }
    public boolean deleteTask(UUID key)
    {
        return true;
    }
    private Task validateTask(Task task)
    {
        if(task.getDescription()==null || task.getDescription().isEmpty())
        {
            logger.warn("Task description is empty. Setting default description.");
            task.setDescription("No Description");
        }
        if(task.getName()==null || task.getName().isEmpty())
        {
            logger.warn("Task name is empty. Setting default name.");
            task.setName("Untitled Task");
        }
        if (task.getDate() == null) {
            logger.warn("Task date is null. Setting today's date.");
            task.setDate(new java.sql.Date(System.currentTimeMillis()));
        }
        if (task.getTime() == null) {
            logger.warn("Task time is null. Setting default time to current time.");
            task.setTime(new java.sql.Time(System.currentTimeMillis()));
        }
        return task;
    }
}
