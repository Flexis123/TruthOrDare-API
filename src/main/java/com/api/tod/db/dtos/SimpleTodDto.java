package com.api.tod.db.dtos;


import com.api.tod.db.models.TodProposed;
import com.api.tod.db.models.TodType;
import com.fasterxml.jackson.annotation.JsonTypeName;


public class SimpleTodDto{
	private String content;
	private TodType type;
	
	public SimpleTodDto(String content, TodType type) {
		this.content = content;
		this.type = type;
	}
	
	public SimpleTodDto() {}

	public String getContent() {
		return content;
	}

	public TodType getType() {
		return type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setType(TodType type) {
		this.type = type;
	}
	
	
}
