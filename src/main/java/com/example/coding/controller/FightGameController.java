package com.example.coding.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.example.coding.service.FightGameService;
import com.example.coding.util.CommonUtil;

@RestController
@ComponentScan
public class FightGameController {

	@Autowired
	private FightGameService fightGameService;

	public String startGame() {
		String response = null;
		boolean isSuccess = false;
		try{
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			CommonUtil.printTitle();
			int startPoint = scan.nextInt();
			if (startPoint == 1) {
				isSuccess = fightGameService.startNewGame();
			} else if (startPoint == 2) {
				isSuccess = fightGameService.loadGame();
			}
			if(isSuccess)
				response = "GAME FINISHED SUCCESSFULLY.";
			else
				response = "GAME FAILED!";
		}catch(Exception ex){
			response = "GAME FAILED!";
			System.exit(0);
		}
		return response;
	}
}
