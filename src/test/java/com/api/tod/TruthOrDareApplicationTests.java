package com.api.tod;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.tod.db.models.Moderator;
import com.api.tod.db.repositories.ModeratorRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class TruthOrDareApplicationTests {

	@Autowired
	ObjectMapper m;
	
	@Test
	void contextLoads() {
		try {
			List<Moderator> n = m.readValue("[{\"username\": \"SergiuG\", \"token\": \"d838af17-10c4-4a46-bcd5-dce4cfac9b21\"}]", new TypeReference<List<Moderator>>() {});
			System.out.println();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
