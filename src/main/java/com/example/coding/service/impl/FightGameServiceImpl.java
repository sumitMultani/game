package com.example.coding.service.impl;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.example.coding.model.Player;
import com.example.coding.service.FightGameService;
import com.example.coding.util.CommonUtil;
import com.example.coding.util.IConstants;

@Service
public class FightGameServiceImpl implements FightGameService {

	@Override
	public boolean startNewGame() {
		boolean isSuccess = true;
		try{
			Scanner scan = new Scanner(System.in);
			Player p1;
			System.out.println("======***==Create Characters By Name===***=======");
			System.out.println("Enter Player1 Name : ");
			String p1Name = scan.next();
			System.out.println("Enter Player2 Name : ");
			String p2Name = scan.next();
			p1 = new Player(p1Name , IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN, p2Name ,IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN);
			CommonUtil.printPlayersName(p1);
			CommonUtil.printPlayersDetail(p1);
			boolean isPlay = true;
			while (isPlay) {
				isPlay = CommonUtil.performActions(scan, p1, isPlay);
			}
		}catch(Exception ex){
			isSuccess = false;
		}
		return isSuccess;
	}

	@Override
	public boolean loadGame() {
		return CommonUtil.loadGame();
	}
	 
}
