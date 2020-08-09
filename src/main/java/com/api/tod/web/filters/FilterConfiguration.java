package com.api.tod.web.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterConfiguration {
	
	private <T extends OncePerRequestFilter> FilterRegistrationBean<T> 
				getRegistrationBean(T filter){
		FilterRegistrationBean<T> reg = new FilterRegistrationBean<>();
		
		reg.setFilter(filter);
		return reg;
	}
	
	@Bean
	public FilterRegistrationBean<AccesFilter> getAcessFilterRegistrationBean(){
		return this.getRegistrationBean(new AccesFilter());
	}
	
	@Bean
	public FilterRegistrationBean<AdminAccesFilter> getAdminFilterRegistrationBean(){
		return this.getRegistrationBean(new AdminAccesFilter());
	}
}
