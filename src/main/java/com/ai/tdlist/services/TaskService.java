package com.ai.tdlist.services;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.enums.TaskStatus;
import com.ai.tdlist.exceptions.RepoCreateException;
import com.ai.tdlist.exceptions.RepoDeleteException;
import com.ai.tdlist.exceptions.RepoFetchException;
import com.ai.tdlist.exceptions.RepoUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
            throw new RepoCreateException(e.toString(),e);
        }

    }
    public Task updateTask(Task task) {
        try {
            logger.info("Updating task in DB: {}", task);
            Task updatedTask = repoService.updateTask(task);
            logger.info("Updated task: {}", updatedTask);
            return updatedTask;
        }
        catch(Exception e)
        {
            logger.error("Error occurred while updating task: {}", e.toString());
            throw new RepoUpdateException(e.toString(),e);
        }
    }
    public void deleteTask(int key)
    {
        try {
            logger.info("Deleting task from DB with key: {}", key);
            repoService.deleteTask(key);
        }
        catch(Exception e)
        {
            logger.error("Error occurred while deleting task: {}", e.toString());
            throw new RepoDeleteException(e.toString(),e);
        }

    }
    public List<Task> getTasksByStatus(TaskStatus status)
    {
        try {
            logger.info("Fetching tasks from DB with status: {}", status);
            return repoService.getTasksByFilters(status);
        }
        catch(Exception e)
        {
            logger.error("Error occurred while fetching tasks: {}", e.toString());
            throw new RepoFetchException(e.toString(),e);
        }
    }

    private void validateTask(Task task)
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
    }
}
