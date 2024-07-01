package com.richard.linktreeClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-secrets.properties")
public class LinktreeCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinktreeCloneApplication.class, args);
	}

	/* TODO - Open api documentation*/
	/* TODO - git ignore*/
}
