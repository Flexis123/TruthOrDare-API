package com.api.tod.services;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Integer32BitIdGeneratorService implements IdGeneratorService<Integer>{

	@Autowired
	SecureRandom r;
	
	@Override
	public Integer generate() {
		return r.nextInt();
	}

}
