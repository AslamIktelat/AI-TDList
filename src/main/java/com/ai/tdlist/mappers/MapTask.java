package com.ai.tdlist.mappers;

import com.ai.tdlist.dtos.Task;
import com.ai.tdlist.entities.TaskEntity;
import com.ai.tdlist.enums.TaskStatus;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MapTask {
    public TaskEntity mapToEntity(Task task)
    {
        TaskEntity taskEntity =new TaskEntity();
        taskEntity.setName(task.getName());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setDate(task.getDate());
        taskEntity.setTime(task.getTime());
        taskEntity.setStatus(task.getStatus().getStatusValue());
        return taskEntity;
    }
    public Task mapToDto(TaskEntity taskEntity)
    {
        Task task=new Task();
        task.setId(taskEntity.getId());
        task.setName(taskEntity.getName());
        task.setDescription(taskEntity.getDescription());
        task.setDate(taskEntity.getDate());
        task.setTime(taskEntity.getTime());
        task.setStatus(TaskStatus.valueOf(taskEntity.getStatus()));
        return task;
    }

    public List<Task> mapToDtoList(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                .map(this::mapToDto)
                .toList();
    }
}
