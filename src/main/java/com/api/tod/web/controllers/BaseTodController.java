package com.api.tod.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.tod.db.dtos.TodDto;
import com.api.tod.db.models.TodProposed;


public abstract class BaseTodController<EN extends TodProposed>{
	
	JpaRepository<EN, Integer> rep;
	ModelMapper mapper;
	
	public BaseTodController(JpaRepository<EN, Integer> rep, ModelMapper mapper) {
		this.rep = rep;
		this.mapper = mapper;
	}
	
	@GetMapping("/get")
	@Transactional
	public List<TodDto> get(@RequestBody EN tod) {
		return rep.findAll(Example.of(tod))
				.stream()
				.map(t -> mapper.map(t, TodDto.class))
				.collect(Collectors.toList());
	}
	
	@PostMapping("/add")
	@Transactional
	public void add(@RequestBody List<EN> tod) {
		tod.forEach(en -> {
			setupEntityForInsert(en);
			rep.save(en);
		});
	}
	
	@PostMapping("/update")
	@Transactional
	public void update(@RequestBody TodDto tod) {
		EN e = rep.findById(tod.getId())
				.orElseThrow(() -> new IllegalArgumentException("entity does not exist"));
		if(tod.getContent() != null) {
			e.setContent(tod.getContent());
		}
		
		if(tod.getType() != null) {
			e.setType(tod.getType());
		}
	}
	
	@DeleteMapping("/delete")
	@Transactional
	public void delete(@RequestParam("id") Integer id) {
		rep.deleteById(id);
	}
	
	public abstract Class<EN> getEntityType();
	public void setupEntityForInsert(EN e) {
		
	}
	
}
