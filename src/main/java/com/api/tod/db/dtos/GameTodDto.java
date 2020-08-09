package com.api.tod.db.dtos;

import com.api.tod.db.models.TodType;

public class GameTodDto extends SimpleTodDto{
	private String challenge;
	private Integer repetitions;
	
	public GameTodDto(String content, TodType type, String challenge, Integer repetitions) {
		super(content, type);
		this.challenge = challenge;
		this.repetitions = repetitions;
	}
	
	public GameTodDto() {
		super();
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public Integer getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Integer repetitions) {
		this.repetitions = repetitions;
	}
	
	
	
}
