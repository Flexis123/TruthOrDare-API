package com.api.tod.web.filters;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.api.tod.web.objects.Connection;

@Component
@Order(1)
public class RateLimitFilter extends HttpFilter{
	
	public static HashMap<String, Connection> connections = new HashMap<>();
	public static final int requestsPerMin = 12;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Connection conn = connections.get(request.getRemoteAddr());
		
		if(conn != null) {
			conn.incrementRequests();
			
			long delta = Instant.now().getEpochSecond() - conn.getTimestampSeconds();
			long curMin = (delta / 60) + 1;
			long allowedRequests = curMin * requestsPerMin;
			
			System.err.println(curMin);
			
			if(conn.getRequests() <= allowedRequests) {
				filterChain.doFilter(request, response);
			}else {
				response.sendError(HttpStatus.TOO_MANY_REQUESTS.value());
			}
		}else {
			connections.put(request.getRemoteAddr(), new Connection());
			filterChain.doFilter(request, response);
		}
	}
	
}
