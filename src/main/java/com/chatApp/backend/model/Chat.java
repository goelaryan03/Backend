package com.chatApp.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String chat_name;
	private String chat_image;
	
	@ManyToMany
	private Set<User> admins=new HashSet<>();
	
	@Column(name="is_group")
	private boolean isGroup;
	
	@JoinColumn(name="created_by")
	@ManyToOne		
	private User createdBy; 
	
	@ManyToMany
	private Set<User> users=new HashSet<>();
	
	@OneToMany
	private List<Message> messages=new ArrayList<>();
	
	public Chat() {
		// TODO Auto-generated constructor stub
	}

	 

	public Set<User> getAdmins() {
		return admins;
	}



	public void setAdmins(Set<User> admins) {
		this.admins = admins;
	}



	public Chat(Integer id, String chat_name, String chat_image, Set<User> admins, boolean isGroup, User createdBy,
			Set<User> users, List<Message> messages) {
		super();
		Id = id;
		this.chat_name = chat_name;
		this.chat_image = chat_image;
		this.admins = admins;
		this.isGroup = isGroup;
		this.createdBy = createdBy;
		this.users = users;
		this.messages = messages;
	}



	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getChat_name() {
		return chat_name;
	}

	public void setChat_name(String chat_name) {
		this.chat_name = chat_name;
	}

	public String getChat_image() {
		return chat_image;
	}

	public void setChat_image(String chat_image) {
		this.chat_image = chat_image;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	 
}
