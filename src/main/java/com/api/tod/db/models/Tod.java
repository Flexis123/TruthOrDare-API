package com.api.tod.db.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Tod extends TodProposed{
	
	@Column(nullable = false)
	private LocalDateTime addedDate;

	public Tod() {
	}

	public Tod(Integer id, String content, TodType type, LocalDateTime addedDate) {
		super(id, content, type);
		this.addedDate = addedDate;
	}

	public Tod(String content, TodType type) {
		super(content, type);
		this.setRegistered();
	}
	
	public void setRegistered() {
		if(addedDate == null) {
			addedDate = LocalDateTime.now();
		}
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}
}
