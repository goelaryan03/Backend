package com.chatApp.backend.model;

import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String content;
	private LocalDateTime timeStamp;
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Chat chat;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(Integer id, String content, LocalDateTime timeStamp, User user, Chat chat) {
		super();
		Id = id;
		this.content = content;
		this.timeStamp = timeStamp;
		this.user = user;
		this.chat = chat;
	}
	
	 
}
