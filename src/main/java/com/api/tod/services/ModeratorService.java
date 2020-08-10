package com.api.tod.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService implements DisposableBean{
	
	@Autowired
	@Qualifier("whitelist")
	private List<String> whitelist;
	
	@Autowired
	private Path p;
	
	public boolean isIn(String id) {
		return whitelist.contains(id);
	}
	
	public boolean isAdmin(String id) {
		return whitelist.get(0).equals(id);	
	}
	
	public boolean appendId() {
		return whitelist.add(UUID.randomUUID().toString());
	}
	
	public boolean removeId(String id) {
		return whitelist.remove(id);
	}
	
	public boolean removeId(UUID id) {
		return removeId(id.toString());
	}
	
	public List<String> getWhitelist() {
		return new ArrayList<>(whitelist);
	}

	@Override
	public void destroy() throws Exception {
		Files.write(p, whitelist);
	}
}
