package com.api.tod.web.filters;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.api.tod.services.ModeratorService;

@Component
@Order(3)
public class AdminAccesFilter extends BaseAccesFilter{
	
	HashSet<String> doFilter = new HashSet<String>() {{
		add("/wh/newModerator");
		add("/wh/remove");
		add("/wh/getModerators");
		add("/wh/newToken");
	}};
	
	@Override
	public Collection<String> getDoFilter() {
		return doFilter;
	}

	@Override
	protected boolean doFilterChainIf(String user, String token, ModeratorService mod) {
		return mod.isAdmin(user, token);
	}
}
