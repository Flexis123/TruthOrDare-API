package com.api.tod;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.api.tod.db.repositories")
@EntityScan("com.api.tod.db.models")
@ComponentScan("com.api.tod")
@EnableWebMvc
@EnableScheduling
public class TruthOrDareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruthOrDareApplication.class, args);
	}
	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public String getCurDir() {
		return System.getProperty("user.dir");
	}	
}
