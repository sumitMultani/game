package com.example.coding.main;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({ "com.example" })
@EntityScan({ "com.example" })
@EnableAutoConfiguration
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				Application.class).headless(false).run(args);
		Application appFrame = context.getBean(Application.class);
	}
}