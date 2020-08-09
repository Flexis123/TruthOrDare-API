package com.api.tod;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Util {
	
	@SuppressWarnings("resource")
	public static ModelMapper getMapper() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TruthOrDareApplication.class);
		return context.getBean("mapper", ModelMapper.class);
	}
}
