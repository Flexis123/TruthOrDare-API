package com.api.tod.db.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepoConfig {
	
	@Autowired
	EntityManager em;
	
	@Bean
	@Primary
	public TodRepoImpl extendedTodRepo() {
		return new TodRepoImpl(em);
	}
	
	@Bean
	@Primary
	public TodProposedRepoImpl extendedProposedTodRepo() {
		return new TodProposedRepoImpl(em);
	}
}
