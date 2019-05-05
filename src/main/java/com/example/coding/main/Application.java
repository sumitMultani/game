package com.example.coding.main;

import java.util.Scanner;

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

	private static CommonUtil commonUtil = new CommonUtil();

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				Application.class).headless(false).run(args);
		FightGameController game = context.getBean(FightGameController.class);
		commonUtil.printTitle();
		Scanner scan = new Scanner(System.in);
		int startPoint = scan.nextInt();
		System.out.println(game.startGame(startPoint));
	}

}