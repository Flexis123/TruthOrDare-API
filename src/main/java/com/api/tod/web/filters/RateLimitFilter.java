package com.api.tod.web.filters;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.api.tod.web.objects.Connection;

@Component
@Order(1)
public class RateLimitFilter extends HttpFilter{
	
	public static HashMap<String, Connection> connections = new HashMap<>();
	
	@Value("${rate.limiter.requestPerMin}")
	private int requestsPerMin = 12;
	
	@Override
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Connection conn = connections.get(request.getRemoteAddr());
		
		if(conn != null) {
			conn.incrementRequests();
			
			long delta = conn.getAliveForSeconds();
			long curMin = (delta / 60) + 1;
			long allowedRequests = curMin * requestsPerMin;
			
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
