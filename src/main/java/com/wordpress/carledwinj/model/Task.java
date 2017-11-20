package com.wordpress.carledwinj.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@NotBlank
	@Size(max = 100)
	private String title;

	private Boolean done = false;

	private Date createdAt = new Date();

	public Task() {
	}

	public Task(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", done=" + done + ", createdAt=" + createdAt + "]";
	}
	
	

}
