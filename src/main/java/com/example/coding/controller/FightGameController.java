package com.example.coding.controller;

import javax.naming.directory.InvalidAttributesException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.example.coding.service.FightGameService;
import com.example.coding.util.IConstants;

@RestController
@ComponentScan
public class FightGameController {

	private static Logger _log = Logger.getLogger(FightGameController.class);
	
	@Autowired
	private FightGameService fightGameService;

	public String startGame(int startPoint) {
		_log.info("[startGame] userInput : "+startPoint);
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
			_log.error("[startGame] Exception : "+ex.getMessage());
			response = IConstants.Response.FAIL;
			System.out.println("Run Again...");
		}
		return response;
	}
}
