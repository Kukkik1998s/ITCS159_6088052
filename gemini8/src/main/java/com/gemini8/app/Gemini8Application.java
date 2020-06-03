package com.gemini8.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "edu.gemini.app.ocs.repositories")
//@EntityScan(basePackages = "edu.gemini.app.ocs.model.DataProcRequirement")
//@ComponentScan(basePackages = "edu.gemini.app.ocs")
public class Gemini8Application {

	public static void main(String[] args) {
		SpringApplication.run(Gemini8Application.class, args);
	}

}
