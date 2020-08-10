package com.api.tod.web.objects;

import java.time.Instant;

public class Connection {
	private int requests = 1;
	private long timestamp;
	private long lastRequestTimestamp;
	
	public Connection(long timestamp) {
		this.timestamp = timestamp;
	}

	public Connection() {
		this.timestamp = timestampNow();
	}
	
	private long timestampNow() {
		return Instant.now().getEpochSecond();
	}

	public int getRequests() {
		return requests;
	}
	
	public void incrementRequests() {
		this.lastRequestTimestamp = this.timestampNow();
		requests++;
	}

	public long getAliveForSeconds() {
		return this.timestampNow() - timestamp;
	}
	
	public long getInactiveForSeconds() {
		return this.timestampNow() - this.lastRequestTimestamp;
	}
}
