package com.api.tod.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.tod.db.models.Moderator;
import com.api.tod.db.repositories.ModeratorRepo;

@Service
public class ModeratorService{
	
	@Autowired
	private ModeratorRepo rep;
	
	@Autowired
	private Moderator admin;
	
	public boolean isAdmin(String username, UUID token) {
		return admin.equals(new Moderator(username, token));
	}
	
	public boolean isAdmin(String username, String token) {
		return isAdmin(username, UUID.fromString(token));
	}
	
	@Transactional
	public boolean isModerator(String username,UUID token) {
		return rep.exists(Example.of(new Moderator(username, token))) || isAdmin(username, token);
	}
	
	public boolean isModerator(String username, String token) {
		return isModerator(username, UUID.fromString(token));
	}
	
}
