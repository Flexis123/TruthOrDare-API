package com.api.tod.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.tod.db.dtos.GameTod;
import com.api.tod.db.models.Tod;

@RestController
@RequestMapping("/tod")
public class TodController extends BaseTodController<Tod>{
	
	
	@GetMapping("/random")
	public GameTod random(@RequestBody HashMap<String, Integer> challenges) {
		Random r = new Random();
		
		int randTod = r.nextInt((int) rep.count());
		
		Tod t = rep.findAll().get(randTod);
		
		GameTod dto = mapper.map(t, GameTod.class);
		if(challenges.size() > 0) {
			int randChallenge = r.nextInt(challenges.size());
			Entry<String, Integer> challenge = new ArrayList<>(challenges.entrySet())
					.get(randChallenge);
			
			dto.setChallenge(challenge.getKey());
			dto.setRepetitions(r.nextInt(challenge.getValue() + 1));
		}
		return dto;	
	}
	
	@Override
	public Class<Tod> getEntityType() {
		return Tod.class;
	}

	@Override
	public void setupEntityForInsert(Tod e) {
		e.setRegistered();
	}
	
}
