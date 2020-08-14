package com.api.tod.services;

import org.springframework.stereotype.Service;

@Service
public interface IdGeneratorService<ID> {
	ID generate();
}
