package com.ai.tdlist.services;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.entities.Taskentite;
import com.ai.tdlist.mappers.MapTask;
import com.ai.tdlist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Service
public class RepoService {
    private static final Logger logger = LoggerFactory.getLogger(RepoService.class);

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    MapTask mapTask;

    public Task createTask(Task task)
    {
        Taskentite taskE =taskRepository.save(mapTask.mapToEntity(task));
        return mapTask.mapToDto(taskE);

    }
    public boolean updateTask(Task task)
    {
        return true;
    }
    public boolean deleteTask(UUID key)
    {
        return true;
    }
}
