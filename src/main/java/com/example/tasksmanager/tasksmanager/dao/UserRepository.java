package com.example.tasksmanager.tasksmanager.dao;

import com.example.tasksmanager.tasksmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
