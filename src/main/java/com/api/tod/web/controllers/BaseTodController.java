package com.api.tod.web.controllers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.tod.db.dtos.TodDto;
import com.api.tod.db.models.TodProposed;
import com.api.tod.db.repositories.BaseTodRepo;

@Component
public abstract class BaseTodController<EN extends TodProposed>{
	
	@Autowired
	BaseTodRepo<EN> brep;
	
	@Autowired
	JpaRepository<EN, Integer> rep;
	
	@Autowired
	ModelMapper mapper;
	
	@Value("${pagination.pageLength}")
	private Integer pages;
	
	protected List<TodDto> toDtoList(Collection<EN> col){
		return col.stream()
			.map(t -> mapper.map(t, TodDto.class))
			.collect(Collectors.toList());
	}
	
	protected List<TodDto> toDtoList(Page<EN> page){
		return this.toDtoList(page.toList());
	}
	
	@GetMapping("/getRecords")
	@Transactional
	public List<TodDto> get(@RequestParam("page") Integer page) {
		return toDtoList(rep.findAll(PageRequest.of(page, pages)));
	}
	
	@GetMapping("/getLike")
	@Transactional
	public List<TodDto> getLike(@RequestBody EN e, @RequestParam("page") Integer page){
		return toDtoList(brep.getBy(e, PageRequest.of(page, pages)));
	}
	
	@GetMapping("/getBy")
	@Transactional
	public List<TodDto> getBy(@RequestBody EN e, @RequestParam("page") Integer page){
		return toDtoList(rep.findAll(Example.of(e), PageRequest.of(page, pages)));
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
