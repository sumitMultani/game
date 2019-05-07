package com.example.coding.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coding.model.Player;

@Component
public class PlayerActions {

	private static Logger _log = Logger.getLogger(PlayerActions.class);
	
	@Autowired
	private PlayerUtil commonUtil;
	
	Scanner scan = new Scanner(System.in);
	
	public boolean performActions(Player playerData, boolean isPlay) {
		commonUtil.printActions(playerData);
		int input = scan.nextInt();
		_log.info("user input : "+input);
		if (input == 1 || input == 5) {
			hitPunch(playerData, input);
		} else if (input == 2 || input == 6) {
			hitKick(playerData, input);
		} else if (input == 3 || input == 7) {
			explorePlayer(playerData, input);
		} else if (input == 4 || input == 8) {
			boostEnergy(playerData, input);
		} else if (input == 9) {
			saveGame(scan, playerData);
			isPlay = false;
		} else if (input == 10) {
			loadGame();
		} else if (input == 11) {
			exitGame();
		} else if (input == 12 || input == 14) {
			fireShortGun(playerData, input);
		} else if (input == 13 || input == 15) {
			fireBigGun(playerData, input);
		}  
		return commonUtil.decideWinnerAndContinue(playerData, isPlay);
	}
	
	public void hitPunch(Player playerData, int input) {
		if(input == 1)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.PUNCH);
		else if(input == 5)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.PUNCH);
		commonUtil.printPlayersDetail(playerData);
	}

	public void hitKick(Player playerData, int input) {
		if(input == 2)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.KICK);
		else if(input == 6)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.KICK);
		commonUtil.printPlayersDetail(playerData);
	}
	
	public void explorePlayer(Player p1, int input) {
		boolean isExplored = true;
		while (isExplored) {
			_log.info("What do you want to buy?");
			_log.info("1. Energy Drink.");
			_log.info("2. short Gun [Cost : 100 Gold]");
			_log.info("3. big Gun [Cost : 200 Gold]");
			_log.info("4. Don't want to Buy Anything.");
			int n = scan.nextInt();
			_log.info("user input : "+n);
			if (n == 1 && input == 3) {
				_log.info("How Many Drink you want to buy ("
						+ IConstants.Price.ENERGY_DRINNK + " gold per piece) : ");
				int drinkCount = scan.nextInt();
				_log.info("user input : "+drinkCount);
				int totalPrice = drinkCount * IConstants.Price.ENERGY_DRINNK;
				if (p1.getpGold() >= totalPrice) {
					_log.info("Purchased successfully.");
					p1.setpGold(p1.getpGold() - totalPrice);
					p1.setpEnergyDrink(p1.getpEnergyDrink() + drinkCount);
					commonUtil.printPlayersDetail(p1);
					isExplored = false;
				} else
					_log.info("You don't have enough money to purchase.");
			} else if (n == 2 && input == 3) {
				if (p1.getpGold() >= IConstants.Price.SHORT_GUN) {
					if (p1.getpShortGun() == 0) {
						p1.setpGold(p1.getpGold() - IConstants.Price.SHORT_GUN);
						p1.setpShortGun((byte)1);
						isExplored = false;
					} else {
						_log.info("You Have Already A Short Gun.");
					}
					commonUtil.printPlayersDetail(p1);
				} else
					_log.info("You don't have enough money to purchase.");
			} else if (n == 3 && input == 3) {
				if (p1.getpGold() >= IConstants.Price.BIG_GUN) {
					if (p1.getpBigGun() == 0) {
						p1.setpGold(p1.getpGold() - IConstants.Price.BIG_GUN);
						p1.setpBigGun((byte)1);
						isExplored = false;
					} else {
						_log.info("You Have Already A Big Gun.");
					}
					commonUtil.printPlayersDetail(p1);
				} else
					_log.info("You don't have enough money to purchase.");
			}else if (n == 1 && input == 7) {
				_log.info("How Many Drink you want to buy ("
						+ IConstants.Price.ENERGY_DRINNK + " gold per piece) : ");
				int drinkCount = scan.nextInt();
				_log.info("user input : "+drinkCount);
				int totalPrice = drinkCount * IConstants.Price.ENERGY_DRINNK;
				if (p1.geteGold() >= totalPrice) {
					_log.info("Purchased successfully.");
					p1.seteGold(p1.geteGold() - totalPrice);
					p1.seteEnergyDrink(p1.geteEnergyDrink() + drinkCount);
					commonUtil.printPlayersDetail(p1);
					isExplored = false;
				} else
					_log.info("You don't have enough money to purchase.");
			} else if (n == 2 && input == 7) {
				if (p1.geteGold() >= IConstants.Price.SHORT_GUN) {
					if (p1.geteShortGun() == 0) {
						p1.seteGold(p1.geteGold() - IConstants.Price.SHORT_GUN);
						p1.seteShortGun((byte)1);
						isExplored = false;
					} else {
						_log.info("You Have Already A Short Gun.");
					}
					commonUtil.printPlayersDetail(p1);
				} else
					_log.info("You don't have enough money to purchase.");
			} else if (n == 3 && input == 7) {
				if (p1.geteGold() >= IConstants.Price.BIG_GUN) {
					if (p1.geteBigGun() == 0) {
						p1.seteGold(p1.geteGold() - IConstants.Price.BIG_GUN);
						p1.seteBigGun((byte)1);
						isExplored = false;
					} else {
						_log.info("You Have Already A Big Gun.");
					}
					commonUtil.printPlayersDetail(p1);
				} else
					_log.info("You don't have enough money to purchase.");
			} else if (n == 4) {
				isExplored = false;
				commonUtil.printPlayersDetail(p1);
			}
		}
	}
	public void boostEnergy(Player playerData, int input) {
		if(input == 4) {
			playerData.setpHealth(playerData.getpHealth() + IConstants.BoostEnergy.BY_DRINK);
			playerData.setpEnergyDrink(playerData.getpEnergyDrink() - 1);
		} else if(input == 8){
			playerData.seteHealth(playerData.geteHealth() + IConstants.BoostEnergy.BY_DRINK);
			playerData.seteEnergyDrink(playerData.geteEnergyDrink() - 1);
		}
		commonUtil.printPlayersDetail(playerData);
	}
	
	private void saveGame(Scanner scan, Player playerData) {
		try {
			_log.info("Enter Game Name Without space : ");
			String gameName = scan.next();
			playerData.setGameName(gameName);
			ReadWrite.writeData(playerData);
			_log.info("Game saved Successfully and Exit");
		} catch (IOException e) {
			_log.error("EXIT DUE TO : " + e.getMessage());
			System.exit(0);
		}
	}
	
	public boolean loadGame() {
		boolean isLoadSuccess = true;
		try {
			Player p1;
			Set<Player> listData = ReadWrite.readData();
			Map<Integer, Player> mapOfSavedGames = new LinkedHashMap<>();
			int count = 1;
			_log.info("Select Number to Load Game : ");
			for (Player player : listData) {
				mapOfSavedGames.put(count, player);
				if(player.getGameName() != null || player.getGameName().equalsIgnoreCase("null")){
					_log.info(count + " : " + player.getGameName());
					count++;
				}
			}
			int selectedCount = scan.nextInt();
			_log.info("user input : "+selectedCount);
			Player player = mapOfSavedGames.get(selectedCount);
			p1 = player;
			_log.info("saved Game Start...");
			commonUtil.printPlayersDetail(p1);
			boolean isPlay = true;
			// repeat action
			while (isPlay) {
				isPlay = performActions(p1, isPlay);
			}
		} catch (Exception e) {
			_log.error("EXIT DUE TO : " + e.getMessage());
			isLoadSuccess = false;
		}
		return isLoadSuccess;
	}

	public void exitGame() {
		_log.info("EXIT SUCCESSFULLY.");
		System.exit(0);
	}
	
	public void fireBigGun(Player playerData, int input) {
		if(input == 13)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.BIG_GUN);
		else if(input == 15)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.BIG_GUN);
			commonUtil.printPlayersDetail(playerData);
	}

	public void fireShortGun(Player playerData, int input) {
		if(input == 12)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.SHORT_GUN);
		else if(input == 14)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.SHORT_GUN);
		commonUtil.printPlayersDetail(playerData);
	}

}
