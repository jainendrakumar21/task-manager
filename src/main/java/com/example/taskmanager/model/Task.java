package com.example.taskmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {
	@Id
	private Integer id;
	
	private String taskName;
	
	private String taskDetails;

	public Task() {
		super();
	}

	public Task(int id, String taskName, String taskDetails) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskDetails = taskDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskDetails=" + taskDetails + "]";
	}
	
	

}
