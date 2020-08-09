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
import com.api.tod.services.WhiteListService;

@Component
@Order(3)
public class AdminAccesFilter extends HttpFilter{
	
	@Autowired
	private WhiteListService wh;
	
	HashSet<String> doFilter = new HashSet<String>() {{
		add("/wh");
	}};

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(wh.isAdmin(request.getHeader("auth"))) {
			filterChain.doFilter(request, response);
		}else {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "only admin can acces this route");
		}
	}

	@Override
	public Collection<String> getDoFilter() {
		return doFilter;
	}

	
}
