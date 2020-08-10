package com.api.tod.db.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
public class Moderator {
	@Id
	@Column(length = 32)
	private String id;
	
	@Column(length=25, nullable=false)
	private String username;

	public Moderator(String id, String username) {
		this.id = id;
		this.username = username;
	}

	public Moderator(String username) {
		this.username = username;
		
	}

	public Moderator() {
	}
	
	
	
}
