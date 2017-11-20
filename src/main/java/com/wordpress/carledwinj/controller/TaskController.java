package com.wordpress.carledwinj.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordpress.carledwinj.model.Task;
import com.wordpress.carledwinj.repository.TaskRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaskController {

	@Autowired
	TaskRepository repository;
	
	@GetMapping(value = "/tasks")
	public List<Task> getAllTasks(){
		
		Sort sortByCreatedAtDesk = new Sort(Sort.Direction.DESC, "createdAt");
		System.out.println("getAllTasks");
		return repository.findAll(sortByCreatedAtDesk);
	}
	
	@PostMapping(value = "/tasks")
	public Task postTask(@Valid @RequestBody Task task) {
		
		task.setDone(false);
		
		return repository.save(task);
	}
	
	@GetMapping(value="/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id){
		
		Task task = repository.findOne(id);
		
		if(null == task) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@PutMapping(value="/tasks/{id}")
	public ResponseEntity<Task> putTask(@PathVariable("id") Integer id, @Valid @RequestBody Task task){
		
		Task taskData = repository.findOne(id);
		
		if(null == taskData) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		taskData.setTitle(task.getTitle());
		taskData.setDone(task.getDone());
		
		return new ResponseEntity<Task>(taskData, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/tasks/{id}")
	public void deleteTask(@PathVariable("id") Integer id) {
		
		repository.delete(id);
	}
	
	
}
