package com.api.tod.db.dtos;

import com.api.tod.db.models.TodType;

public class GameTod extends SimpleTodDto{
	private String challenge;
	private Integer repetitions;
	
	public GameTod(String content, TodType type, String challenge, Integer repetitions) {
		super(content, type);
		this.challenge = challenge;
		this.repetitions = repetitions;
	}
	
	public GameTod() {
		super();
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		if(challenge.length() > 35) {
			throw new IllegalArgumentException("challenge cannot have more than 35 characters");
		}
		this.challenge = challenge;
	}

	public Integer getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Integer repetitions) {
		if(repetitions < 1) {
			throw new IllegalArgumentException("repetitions cannot be less than 1!!");
		}
		this.repetitions = repetitions;
	}
	
	
	
}
