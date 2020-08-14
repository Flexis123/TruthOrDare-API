package com.api.tod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.DefaultPropertiesPersister;

public class Util {
	
	public static ApplicationContext getContext() {
		return new AnnotationConfigApplicationContext(TruthOrDareApplication.class);	
	}
	
	public static void saveProperties(Properties props, String file) {
		DefaultPropertiesPersister per = new DefaultPropertiesPersister();
		try {
			per.store(props, new FileOutputStream(new File(file)), "");
		} catch (FileNotFoundException e) {
			createFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Properties loadProperties(String file) {
		DefaultPropertiesPersister per = new DefaultPropertiesPersister();
		
		Properties props = new Properties();
		File f = new File(file);
		try {
			per.load(props, new FileInputStream(f));
		} catch (FileNotFoundException e) {
			createFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static Properties loadPropertiesClassPath(String name) {
		InputStream in = Util.class.getClassLoader().getResourceAsStream(name);
		
		Properties props = new Properties();
		
		DefaultPropertiesPersister per = new DefaultPropertiesPersister();
		try {
			per.load(props, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	public static void createFile(String file) {
		try {
			Files.createFile(Paths.get(file));
			loadProperties(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
