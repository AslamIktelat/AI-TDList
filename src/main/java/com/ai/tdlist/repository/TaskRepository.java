package com.ai.tdlist.repository;

import com.ai.tdlist.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    public TaskEntity save(TaskEntity taskEntity);
    public TaskEntity getById(int id);
    public void deleteById(int id);
    public List<TaskEntity> findAllByStatus(String status);
}
