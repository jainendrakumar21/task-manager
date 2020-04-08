package com.example.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Error;
import com.example.taskmanager.model.ServiceResponse;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repo.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskRepository taskrepo;

	@GetMapping
	public ServiceResponse<List<Task>> getAllTasks() {
		final ServiceResponse<List<Task>> response = new ServiceResponse<>();
		final List<Task> tasks = taskrepo.findAll();
		if (!CollectionUtils.isEmpty(tasks)) {
			response.setData(tasks);
		} else {
			final Error error = new Error();
			error.setMessage("No records found, add some!");
			error.setErrorStatus(HttpStatus.NO_CONTENT.name());
			response.setError(error);
		}

		return response;
	}

	@GetMapping("/{taskId}")
	public ServiceResponse<Task> getByTaskId(@PathVariable Integer taskId) {
		final ServiceResponse<Task> response = new ServiceResponse<>();
		Optional<Task> taskOptional = taskrepo.findById(taskId);
		if (taskOptional.isPresent()) {
			response.setData(taskOptional.get());
		} else {
			final Error error = new Error();
			error.setMessage("No record found with id: " + taskId);
			error.setErrorStatus(HttpStatus.NO_CONTENT.name());
			response.setError(error);
		}
		return response;
	}

	@PostMapping("/save")
	public ServiceResponse<Task> saveTask(@RequestBody Task task) {
		final ServiceResponse<Task> response = new ServiceResponse<>();
		taskrepo.save(task);
		response.setData(task);
		return response;
	}

	@PutMapping("/update/{taskId}")
	public ServiceResponse<Task> updateTask(@PathVariable Integer taskId, @RequestBody Task newTask) {
		final ServiceResponse<Task> response = new ServiceResponse<>();
		Optional<Task> taskOptional = taskrepo.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			task.setTaskDetails(newTask.getTaskDetails());
			task.setTaskName(newTask.getTaskName());
			taskrepo.save(task);
			response.setData(task);
		} else {
			final Error error = new Error();
			error.setMessage("No record found with id: " + taskId);
			error.setErrorStatus(HttpStatus.NO_CONTENT.name());
			response.setError(error);
		}
		return response;
	}

	@DeleteMapping("/delete/{taskId}")
	public ServiceResponse<Task> deleteTask(@PathVariable Integer taskId) {
		final ServiceResponse<Task> response = new ServiceResponse<>();
		Optional<Task> taskOptional = taskrepo.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			taskrepo.delete(task);
			response.setData(task);
		} else {
			final Error error = new Error();
			error.setMessage("No record found with id: " + taskId);
			error.setErrorStatus(HttpStatus.NO_CONTENT.name());
			response.setError(error);
		}
		return response;
	}

}
