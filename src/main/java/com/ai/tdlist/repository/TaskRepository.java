package com.ai.tdlist.repository;

import com.ai.tdlist.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    public TaskEntity save(TaskEntity taskEntity);
    public TaskEntity getById(int id);
    public void deleteById(int id);
    public List<TaskEntity> findAllByStatus(String status);
}
