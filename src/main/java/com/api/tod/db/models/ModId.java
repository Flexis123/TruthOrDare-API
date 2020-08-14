package com.api.tod.db.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class ModId implements Serializable{
	
	private static final long serialVersionUID = -7797458052682554338L;

	@GeneratedValue
	private UUID uuid;
	
	@Column(length=25)
	private String username;

	public ModId(UUID id, String username) {
		this.uuid = id;
		this.username = username;
	}
	
	public ModId(String username) {
		this.username = username;
		this.uuid = UUID.randomUUID();
	}
	
	public ModId(UUID uuid) {
		this.uuid = uuid;
	}

	public ModId() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username.length() > 25) {
			throw new IllegalArgumentException("username cannot be longer than 25 characters");
		}
		this.username = username;
	}

	public UUID getUUID() {
		return uuid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModId other = (ModId) obj;
		return Objects.equals(uuid, other.uuid) && Objects.equals(username, other.username);
	}
}
