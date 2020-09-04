package com.api.tod.web.controllers;

import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tod.Util;

@RestController
@RequestMapping("/props")
public class ApiPropsController {
	
	@GetMapping("/mod")
	public Properties getPagination() {
		Properties props = Util.loadPropertiesClassPath("pagination.properties");
		props.putAll(Util.loadPropertiesClassPath("admin.properties"));
		return props;
	}
	
}
