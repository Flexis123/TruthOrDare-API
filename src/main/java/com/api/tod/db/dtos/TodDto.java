package com.api.tod.db.dtos;

import com.api.tod.db.models.TodType;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("TodDto")
public class TodDto extends SimpleTodDto{
	private Integer id;
	
	public TodDto(Integer id, String content, TodType type) {
		super(content, type);
		this.id = id;
	}
	
	public TodDto() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
