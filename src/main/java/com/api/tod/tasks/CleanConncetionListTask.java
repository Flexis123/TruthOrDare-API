package com.api.tod.tasks;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.api.tod.web.filters.RateLimitFilter;
import com.api.tod.web.objects.Connection;

@Component
public class CleanConncetionListTask implements Runnable{
	
	public static final int redundantTimeoutSeconds = 900;
	public static final int fixedRateMiliseconds = 60000;

	@Override
	@Scheduled(fixedRate = fixedRateMiliseconds)
	public void run() {
		Map<String, Connection> conn = RateLimitFilter.connections;
		
		conn = conn.entrySet()
				.stream()
				.filter(en -> {	
					Connection con = en.getValue();
					if(con.getInactiveForSeconds() > redundantTimeoutSeconds) {
						return false;
					}
					return true;
				})
				.collect(Collectors.toMap(en -> en.getKey(), en -> en.getValue()));
				
	}
	
}
