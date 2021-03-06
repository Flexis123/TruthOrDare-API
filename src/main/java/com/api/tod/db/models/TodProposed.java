package com.api.tod.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TodProposed{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 75, nullable = false)
	private String content;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TodType type;

	public TodProposed(Integer id, String content, TodType type) {
		this.id = id;
		this.setContent(content);
		this.type = type;
	}

	public TodProposed(String content, TodType type) {
		this(null, content, type);
	}

	public TodProposed() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content.length() > 75) {
			throw new IllegalArgumentException("content cannot be longer than 75 characters");
		}
	}

	public TodType getType() {
		return type;
	}

	public void setType(TodType type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	
}
