package com.api.tod.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@PostMapping("/newModerator")
	public void addNewModerator(@RequestHeader("name") String name) {
		rep.save(new Moderator(name));
	}
	
	@DeleteMapping("/remove")
	public void remove(@RequestBody Moderator mod) {
		rep.delete(mod);
	}
	
	@GetMapping("/getModerators")
	public List<Moderator> getWhitelist(@RequestParam("page") Integer page){
		return rep.findAll(PageRequest.of(page, len))
			.filter(mod -> !mod.equals(admin))
			.toList();
	}
	
	@GetMapping("/login")
	public void login(@RequestBody Moderator mod){
		rep.findOne(Example.of(mod))
			.orElseThrow(() -> new HttpException(HttpStatus.UNAUTHORIZED, "username does not exist"));	
		
	}
}
