package com.example.tasksmanager.tasksmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TasksManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksManagerApplication.class, args);
	}

}
