package com.api.tod.web.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import com.api.tod.services.ModeratorService;


public abstract class BaseAccesFilter extends HttpFilter{
	
	@Value("${mod.tokenHeader}")
	private String tokenHeader;
	
	@Value("${mod.userHeader}")
	private String userHeader;
	
	@Autowired
	private ModeratorService mod;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = Optional.ofNullable(request.getHeader(tokenHeader)).orElse("");
		String user = Optional.ofNullable(request.getHeader(userHeader)).orElse("");
		
		if(this.doFilterChainIf(user, token, mod)) {
			filterChain.doFilter(request, response);
		}else {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "only admin and mods can acces this route");
		}
	}
	
	protected abstract boolean doFilterChainIf(String user, String token, ModeratorService mod);
	
}
