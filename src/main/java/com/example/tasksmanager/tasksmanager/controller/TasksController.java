package com.example.tasksmanager.tasksmanager.controller;


import com.example.tasksmanager.tasksmanager.dao.TaskRepository;
import com.example.tasksmanager.tasksmanager.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class TasksController {

    @Autowired
    private TaskRepository taskRepo;


    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Tasks>> getALlTasks(@PathVariable Long userId){
        try {
            List<Tasks> allTasks = taskRepo.findAllByUserId(userId);

            if (allTasks.isEmpty()){
                return new ResponseEntity<>(allTasks, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allTasks, HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<Tasks> getTaskDetails(@PathVariable Long userId, @PathVariable Long taskId){
        List<Tasks> allTasksForUser = taskRepo.findAllByUserId(userId);


        for (Tasks tasks: allTasksForUser){
            if (tasks.getId().equals(taskId)) {
                taskRepo.findById(taskId);
                return new ResponseEntity<>(tasks, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/{userId}/tasks")
    public ResponseEntity<Tasks> createTasks(@PathVariable Long userId, @RequestBody Tasks task){
        task.setUserId(userId);
        task.setStatus("pending");
        Tasks createdTask =taskRepo.save(task);
        return new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

    @PutMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Tasks newTaskData){
        List<Tasks> listAllTask = taskRepo.findAllByUserId(userId);

        for (Tasks task: listAllTask ){
            if (task.getId().equals(taskId)){
                task.setName(newTaskData.getName());
                task.setDescription(newTaskData.getDescription());
                task.setDateTime(newTaskData.getDateTime());

                Tasks newTask = taskRepo.save(task);
                return new ResponseEntity<>(newTask, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTasks(@PathVariable Long userId, @PathVariable Long taskId){
        try {
            List<Tasks> allTasks = taskRepo.findAllByUserId(userId);

            for (Tasks task : allTasks) {
                if (task.getId().equals(taskId)) {
                    taskRepo.deleteById(taskId);
                    return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
                }
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
