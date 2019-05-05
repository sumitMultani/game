package com.example.coding.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.coding.controller.FightGameController;
import com.example.coding.util.CommonUtil;

@SpringBootApplication
@ComponentScan({ "com.example" })
@EntityScan({ "com.example" })
@EnableAutoConfiguration
@EnableScheduling
public class Application {

	@Autowired
	private CommonUtil commonUtil;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				Application.class).headless(false).run(args);
		FightGameController game = context.getBean(FightGameController.class);
		game.startGame();
	}

}