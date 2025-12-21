package com.ai.tdlist.mappers;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.entities.Taskentite;
import com.ai.tdlist.enums.TaskStatus;
import org.springframework.stereotype.Service;

@Service
public class MapTask {
    public Taskentite mapToEntity(Task task)
    {
        Taskentite taskentite=new Taskentite();
        taskentite.setName(task.getName());
        taskentite.setDescription(task.getDescription());
        taskentite.setDate(task.getDate());
        taskentite.setTime(task.getTime());
        taskentite.setStatus(task.getStatus().getStatus());
        return taskentite;
    }
    public Task mapToDto(Taskentite taskentite)
    {
        Task task=new Task();
        task.setId(taskentite.getId());
        task.setName(taskentite.getName());
        task.setDescription(taskentite.getDescription());
        task.setDate(taskentite.getDate());
        task.setTime(taskentite.getTime());
        task.setStatus(TaskStatus.valueOf(taskentite.getStatus()));
        return task;
    }
}
