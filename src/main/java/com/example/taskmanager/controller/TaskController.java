package com.example.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repo.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskRepository taskrepo;

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		return new ResponseEntity<>(taskrepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getByTaskId(@PathVariable Integer taskId) {
		Optional<Task> taskOptional = taskrepo.findById(taskId);
		if (taskOptional.isPresent()) {
			return new ResponseEntity<>(taskOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Task> saveTask(@RequestBody Task task) {
		taskrepo.save(task);
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody Task newTask) {
		Optional<Task> taskOptional = taskrepo.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			task.setTaskDetails(newTask.getTaskDetails());
			task.setTaskName(newTask.getTaskName());
			taskrepo.save(task);
			return new ResponseEntity<>(task, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<Task> deleteTask(@PathVariable Integer taskId) {
		Optional<Task> taskOptional = taskrepo.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			taskrepo.delete(task);
			return new ResponseEntity<>(task, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
