package com.api.tod.services;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.api.tod.db.models.Moderator;
import com.api.tod.db.repositories.ModeratorRepo;

@Configuration
@PropertySource("classpath:admin.properties")
public class ServiceConfig {
	
	@Autowired
	ModeratorRepo mod;
	
	@Value("${admin.username}")
	private String name;
	
	@Bean
	public Moderator getAdmin() {
		return mod.findById(name).orElseGet(() -> {
			mod.save(new Moderator(name));
			return mod.findById(name).get();
		});
	}
	
	@Bean
	public SecureRandom getGenerator() {
		return new SecureRandom();
	}
}
