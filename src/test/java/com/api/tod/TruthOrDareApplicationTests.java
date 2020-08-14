package com.api.tod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.tod.db.models.Moderator;
import com.api.tod.db.repositories.ModeratorRepo;

@SpringBootTest
class TruthOrDareApplicationTests {

	@Autowired
	ModeratorRepo em;
	
	@Value("${admin.username}")
	String name;
	
	@Autowired
	Moderator admin;
	
	@Test
	void contextLoads() {
		em.findOneByUsername(name).ifPresent(m -> {
			System.out.println(m.equals(admin));
		});;
	}
	
}
