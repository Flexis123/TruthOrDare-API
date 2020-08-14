package com.api.tod.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tod.db.models.TodProposed;
import com.api.tod.db.repositories.BaseTodRepo;

@RestController
@RequestMapping("/tod_proposed")
public class TodProposedController extends BaseTodController<TodProposed>{
	
	@Override
	public Class<TodProposed> getEntityType() {
		return TodProposed.class;
	}
}
