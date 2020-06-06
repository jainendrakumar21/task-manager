package com.example.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.taskmanager.model.Error;
import com.example.taskmanager.model.ServiceResponse;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repo.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	private TaskRepository taskrepo;

	@GetMapping
	public @ResponseBody ServiceResponse<List<Task>> getAllTasks() {
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
	public @ResponseBody ServiceResponse<Task> getByTaskId(@PathVariable Integer taskId) {
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
	public @ResponseBody ServiceResponse<Task> saveTask(@RequestBody Task task) {
		final ServiceResponse<Task> response = new ServiceResponse<>();
		taskrepo.save(task);
		response.setData(task);
		return response;
	}

	@PutMapping("/update/{taskId}")
	public @ResponseBody ServiceResponse<Task> updateTask(@PathVariable Integer taskId, @RequestBody Task newTask) {
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
	public @ResponseBody ServiceResponse<Task> deleteTask(@PathVariable Integer taskId) {
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

	@GetMapping("/ip")
	public @ResponseBody String getClientIp() {
		String ip = getClientIp(request);
		return "Your current Public IP address: " + ip;
	}

	@GetMapping("/abhishek")
	public String abhishek() {
		return "abhishek";

	}

	@GetMapping("/dimple")
	public String dimple() {
		return "dimple";

	}

	@GetMapping("/neha")
	public String neha() {
		return "neha";

	}

	@GetMapping("/ragini")
	public String ragini() {
		return "hello";

	}

	@GetMapping("/niku")
	public String niku() {
		return "hello";

	}

	private static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

}
