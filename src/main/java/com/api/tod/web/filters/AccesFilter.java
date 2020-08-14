package com.api.tod.web.filters;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.api.tod.services.ModeratorService;

@Component
@Order(2)
public class AccesFilter extends BaseAccesFilter{

	private HashSet<String> doFilter = new HashSet<String>() {{
		add("/update");
		add("/delete");
		add("/get");
		add("/tod/add");
		add("/props");
	}};
	
	@Override
	public Collection<String> getDoFilter() {
		return doFilter;
	}

	@Override
	protected boolean doFilterChainIf(String user, String token, ModeratorService mod) {
		return mod.isModerator(user, token);
	}
}
