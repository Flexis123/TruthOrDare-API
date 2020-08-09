package com.api.tod.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tod.services.WhiteListService;

@RestController
@RequestMapping("/wh")
public class WhiteListController {
	
	@Autowired
	WhiteListService wh;
	
	@PostMapping("/new_id")
	public void addNewId() {
		wh.appendId();
	}
	
	@DeleteMapping("/remove")
	public void removeId(@RequestParam("id") String id) {
		wh.removeId(id);
	}
	
	@GetMapping("/get_whitelist")
	public List<String> getWhitelist(){
		return wh.getWhitelist();
	}
}
