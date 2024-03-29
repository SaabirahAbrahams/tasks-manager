package com.example.tasksmanager.tasksmanager.dao;

import com.example.tasksmanager.tasksmanager.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findAllByUserId(Long userId);
}
