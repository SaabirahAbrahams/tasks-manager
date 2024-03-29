package com.example.tasksmanager.tasksmanager.service;

import com.example.tasksmanager.tasksmanager.dao.TaskRepository;
import com.example.tasksmanager.tasksmanager.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    private TaskRepository taskRepo;

    @Scheduled(fixedRate = 60000)
    public void updateTaskStatus(){
        List<Tasks> getAllTasks = taskRepo.findAll();
        for (Tasks task: getAllTasks){
            if (task.getStatus().equals("pending")){
                task.setStatus("done");
                taskRepo.save(task);
            }
        }
    }


}
