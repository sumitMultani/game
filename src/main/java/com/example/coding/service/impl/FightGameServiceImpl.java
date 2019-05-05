package com.example.coding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coding.model.Player;
import com.example.coding.service.FightGameService;
import com.example.coding.util.CommonUtil;
import com.example.coding.util.IConstants;
import com.example.coding.util.PlayerActions;

@Service
public class FightGameServiceImpl implements FightGameService {

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private PlayerActions playerActions;
	
	@Override
	public boolean startNewGame() {
		boolean isStartSuccess = true;
		try{
			List<String> playerNames = commonUtil.getPlayerNames();
			Player p1 = new Player(playerNames.get(0), IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN, playerNames.get(1) ,IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN);
			commonUtil.printPlayersName(p1);
			commonUtil.printPlayersDetail(p1);
			boolean isPlay = true;
			while (isPlay) {
				isPlay = playerActions.performActions(p1, isPlay);
			}
		}catch(Exception ex){
			System.out.println("Error : "+ex);
			isStartSuccess = false;
		}
		return isStartSuccess;
	}


	@Override
	public boolean loadGame() {
		boolean isLoadSuccess = true;
		try{
			isLoadSuccess = playerActions.loadGame();
		}catch(Exception ex){
			isLoadSuccess = false;
		}
		return isLoadSuccess;
	}
	 
}
