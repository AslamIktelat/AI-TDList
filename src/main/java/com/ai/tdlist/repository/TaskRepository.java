package com.ai.tdlist.repository;

import com.ai.tdlist.entities.Taskentite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Taskentite, Integer> {
    // Additional query methods can be defined here if needed
    //added function for saving task entity
    public Taskentite save(Taskentite taskentite);
}
