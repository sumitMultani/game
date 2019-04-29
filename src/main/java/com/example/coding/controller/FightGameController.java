package com.example.coding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coding.service.FightGameService;

@RestController
@ComponentScan
public class FightGameController {

	@Autowired
	private FightGameService fightGameService;

	@RequestMapping("/")
	public String GameStart() {
		try {
			fightGameService.gameStart();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return "Greetings from Spring Boot!";
	}
}
