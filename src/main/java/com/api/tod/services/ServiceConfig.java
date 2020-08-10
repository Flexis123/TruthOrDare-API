package com.api.tod.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
	
	@Autowired
	private String curDir;
	
	@Bean
	public Path getWhitelistPath() {
		return Paths.get(curDir, "whitelist.txt");
	}
	
	@Bean
	public Moderator getAdmin() {
		
	}
	
	@Bean
	public SecureRandom getGenerator() {
		return new SecureRandom();
	}
}
