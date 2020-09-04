package com.api.tod.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tod.db.models.Moderator;
import com.api.tod.db.repositories.ModeratorRepo;
import com.api.tod.exceptions.HttpException;
import com.api.tod.services.ModeratorService;

@RestController
@RequestMapping("/wh")
public class ModeratorController {
	
	@Autowired
	ModeratorService wh;
	
	@Autowired
	ModeratorRepo rep;
	
	@Autowired
	Moderator admin;
	
	@Value("${pagination.modPageLength}")
	Integer len;
	
	@PostMapping("/newModerators")
	public void addNewModerator(@RequestBody List<String> name) {
		rep.saveAll(name.stream()
				.map(s -> new Moderator(s))
				.collect(Collectors.toList())
		);
	}
	
	@DeleteMapping("/remove_moderators")
	public void remove(@RequestBody List<Moderator> mod) {
		rep.deleteAll(mod);
	}
	
	@GetMapping("/getModerators")
	public List<Moderator> getWhitelist(@RequestParam("page") Integer page){
		return rep.findAll(PageRequest.of(page, len))
			.filter(mod -> !mod.equals(admin))
			.toList();
	}
	
	@GetMapping("/login")
	public Moderator login(@RequestBody Moderator mod){
		Moderator m = rep.findById(mod.getUsername())
			.orElseThrow(() -> new HttpException(HttpStatus.UNAUTHORIZED, "username does not exist"));	
		
		if(!m.getToken().equals(mod.getToken())) {
			throw new HttpException(HttpStatus.UNAUTHORIZED, "invalid token");
		}
		return m;
	}
	
	@GetMapping("/newTokens")
	public List<UUID> newToken(@RequestBody List<String> names) {
		
		List<UUID> uuids = new ArrayList<>();
		names.forEach(name -> {
			Moderator mod = rep.findById(name)
					.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "username does not exist"));
			
			mod.newToken();
			uuids.add(mod.getToken());
		});
		return uuids;
	}
}
