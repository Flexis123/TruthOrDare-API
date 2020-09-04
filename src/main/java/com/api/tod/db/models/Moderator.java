package com.api.tod.db.models;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Moderator {
	
	@Id
	@Column(length = 25)
	private String username;
	
	private UUID token;

	public Moderator(String username, UUID token) {
		this.username = username;
		this.token = token;
	}

	public Moderator(String username) {
		if(username.length() < 25) {
			this.username = username;
		}else {
			throw new IllegalArgumentException("username cannot be longer than 25 characters");
		}
		this.newToken();
	}

	public Moderator() {
	}

	public String getUsername() {
		return username;
	}

	public UUID getToken() {
		return token;
	}
	
	public void newToken() {
		token = UUID.randomUUID();
	}

	@Override
	public int hashCode() {
		return Objects.hash(token, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moderator other = (Moderator) obj;
		return Objects.equals(token, other.token) && Objects.equals(username, other.username);
	}
}
