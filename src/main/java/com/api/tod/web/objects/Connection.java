package com.api.tod.web.objects;

import java.time.Instant;

public class Connection {
	private int requests = 1;
	private Long timestamp;
	
	public Connection(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Connection() {
		this.timestamp = Instant.now().getEpochSecond();
	}

	public int getRequests() {
		return requests;
	}
	
	public void incrementRequests() {
		requests++;
	}

	public long getTimestampSeconds() {
		return timestamp;
	}
}
