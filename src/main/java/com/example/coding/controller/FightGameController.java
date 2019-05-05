package com.example.coding.controller;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.example.coding.service.FightGameService;
import com.example.coding.util.IConstants;

@RestController
@ComponentScan
public class FightGameController {

	@Autowired
	private FightGameService fightGameService;

	public String startGame(int startPoint) {
		String response = IConstants.Response.SUCCESS;
		boolean isStartSucess = false;
		boolean isLoadSucess = false;
		try{
			if (startPoint == 1) {
				  isStartSucess = fightGameService.startNewGame();
			} else if (startPoint == 2) {
				  isLoadSucess = fightGameService.loadGame();
			} else if (!isStartSucess && !isLoadSucess && startPoint > 2) {
				throw new InvalidAttributesException();
			}
		}catch(Exception ex){
			response = IConstants.Response.FAIL;
		}
		return response;
	}
}
