package com.example.coding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.example.coding.service.FightGameService;

@RestController
@ComponentScan
public class FightGameController {

	@Autowired
	private FightGameService fightGameService;

	public void startGame() {
		 fightGameService.startGame();
	}
}
