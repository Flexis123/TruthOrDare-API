package com.api.tod.web.filters;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class HttpFilter extends OncePerRequestFilter{
	public Collection<String> getDoFilter(){
		return null;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		Collection<String> doFilter = this.getDoFilter();
		if(doFilter == null || doFilter.size() == 0) {
			return false;
		}
		
		String uri = request.getRequestURI();
		return !doFilter.stream()
					.anyMatch(s -> uri.contains(s));
	}
	
}
