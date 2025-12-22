package com.ai.tdlist.services;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.entities.TaskEntity;
import com.ai.tdlist.enums.TaskStatus;
import com.ai.tdlist.mappers.MapTask;
import com.ai.tdlist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class RepoService {
    private static final Logger logger = LoggerFactory.getLogger(RepoService.class);

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    MapTask mapTask;

    public Task createTask(Task task)
    {
        TaskEntity taskE =taskRepository.save(mapTask.mapToEntity(task));
        return mapTask.mapToDto(taskE);

    }
    public Task updateTask(Task task)
    {
        TaskEntity currentTask= taskRepository.getById(task.getId());
        logger.info("Current task fetched from DB: {}", currentTask);
        TaskEntity updatedTaskEntite =taskRepository.save(updateRequest(task,currentTask));
        return mapTask.mapToDto(updatedTaskEntite);
    }
    public void deleteTask(int key)
    {
        taskRepository.deleteById(key);
    }

    public List<Task> getTasksByFilters(TaskStatus status) {
        List<TaskEntity> taskEntities = taskRepository.findAllByStatus(status.getStatusValue());
        return mapTask.mapToDtoList(taskEntities);
    }
    private TaskEntity updateRequest(Task task, TaskEntity currentTask)
    {

        if(task.getDescription()!=null && !task.getDescription().isEmpty())
        {
            currentTask.setDescription(task.getDescription());
            logger.info("Updated description to: {}", task.getDescription());
        }
        if(task.getName()!=null && !task.getName().isEmpty())
        {
            currentTask.setName(task.getName());
            logger.info("Updated name to: {}", task.getName());
        }
        if (task.getDate() != null) {
            currentTask.setDate(task.getDate());
            logger.info("Updated date to: {}", task.getDate());
        }
        if (task.getTime() != null) {
            currentTask.setTime(task.getTime());
            logger.info("Updated time to: {}", task.getTime());
        }
        if(task.getStatus()!=null)
        {
            currentTask.setStatus(task.getStatus().getStatusValue());
            logger.info("Updated status to: {}", task.getStatus());
        }
        return currentTask;
    }
}
