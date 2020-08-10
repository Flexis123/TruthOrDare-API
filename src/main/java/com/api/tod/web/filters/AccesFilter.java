package com.api.tod.web.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.api.tod.services.ModeratorService;

@Component
@Order(2)
public class AccesFilter extends HttpFilter{

	private HashSet<String> doFilter = new HashSet<String>() {{
		add("/update");
		add("/delete");
		add("/get");
		add("/tod/add");
	}};
	
	@Autowired
	ModeratorService whitelist;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(whitelist.isIn(request.getHeader("auth"))) {
			filterChain.doFilter(request, response);
		}else {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "only admin and mods can acces this route");
		}
	}

	@Override
	public Collection<String> getDoFilter() {
		return doFilter;
	}
}
