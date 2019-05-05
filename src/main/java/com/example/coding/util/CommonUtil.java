package com.example.coding.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.coding.model.Player;

@Component
public class CommonUtil {

	public static void printTitle(){
				System.out.println(" ________  _________  ________  _______   _______  _________        ________ ___  ________  ___  ___  _________  _______   ________          ___  ___");
				System.out.println("|\\   ____\\|\\___   ___|\\   __  \\|\\  ___ \\ |\\  ___ \\|\\___   ___\\     |\\  _____|\\  \\|\\   ____\\|\\  \\|\\  \\|\\___   ___|\\  ___ \\ |\\   __  \\        |\\  \\|\\  \\ ");
				System.out.println("\\ \\  \\___|\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|\\ \\   __/\\|___ \\  \\_|     \\ \\  \\__/\\ \\  \\ \\  \\___|\\ \\  \\\\\\  \\|___ \\  \\_\\ \\   __/|\\ \\  \\|\\  \\       \\ \\  \\ \\  \\  ");
				System.out.println(" \\ \\_____  \\   \\ \\  \\ \\ \\   _  _\\ \\  \\_|/_\\ \\  \\_|/__  \\ \\  \\       \\ \\   __\\\\ \\  \\ \\  \\  __\\ \\   __  \\   \\ \\  \\ \\ \\  \\_|/_\\ \\   _  _\\       \\ \\  \\ \\  \\  ");
				System.out.println("  \\|____|\\  \\   \\ \\  \\ \\ \\  \\\\  \\\\ \\  \\_|\\ \\ \\  \\_|\\ \\  \\ \\  \\       \\ \\  \\_| \\ \\  \\ \\  \\|\\  \\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\_|\\ \\ \\  \\\\  \\|       \\ \\  \\ \\  \\ ");
				System.out.println("    ____\\_\\  \\   \\ \\__\\ \\ \\__\\\\ _\\\\ \\_______\\ \\_______\\  \\ \\__\\       \\ \\__\\   \\ \\__\\ \\_______\\ \\__\\ \\__\\   \\ \\__\\ \\ \\_______\\ \\__\\\\ _\\        \\ \\__\\ \\__\\");
				System.out.println("   |\\_________\\   \\|__|  \\|__|\\|__|\\|_______|\\|_______|   \\|__|        \\|__|    \\|__|\\|_______|\\|__|\\|__|    \\|__|  \\|_______|\\|__|\\|__|        \\|__|\\|__|");
				System.out.println("   \\|_________|                                                                                                                                           ");
				printDashLine();		
				System.out.println("WELCOME! GAME SELECTION...");		
				System.out.println("1. Start a New Game\n2. Load Game");		
				printDashLine();
				System.out.println("(Select 1 or 2) : ");
	}
	
	public static void printPlayersName(Player p1) {
		System.out
				.println("=======================START FIGHT===============================");
		System.out.println("***********************  "
				+ p1.getpName().toUpperCase() + " vs "
				+ p1.geteName().toUpperCase() + "  ******************");
		printDashLine();
	}
	
	public static void printPlayersDetail(Player p1) {
		System.out.println(p1);
	}
	
	public static void printActions(Player p1) {
		System.out.println("Points :");
		printDashLine();
		System.out
				.println("[Punch = -5, Kick = -10, Explore = Buy Products, Energy = +50]");
		printDashLine();
		System.out.println("What do you want to do ?");
		System.out.println("1. Punch by P1");
		System.out.println("2. Kick by P1");
		System.out.println("3. Explore P1");
		if (p1.getpEnergyDrink() > 0) {
			System.out.println("4. Boost Energy P1");
		}
		System.out.println("5. Punch by P2");
		System.out.println("6. Kick by P2");
		System.out.println("7. Explore  P2");
		if (p1.geteEnergyDrink() > 0) {
			System.out.println("8. Boost Energy P2");
		}
		System.out.println("9. Save And Exit Game");
		System.out.println("10. Open Saved Games");
		System.out.println("11. Exit");
		if (p1.getpShortGun() == 1) {
			System.out.println("12. Short Gun Fire p1");
		}
		if (p1.getpBigGun() == 1) {
			System.out.println("13. Big Gun Fire p1");
		}
		if (p1.geteShortGun() == 1) {
			System.out.println("14. Short Gun Fire p2");
		}
		if (p1.geteBigGun() == 1) {
			System.out.println("15. Big Gun Fire p2");
		}
	}
	
	
	public static void fireBigGun(Player playerData, int input) {
		if(input == 13)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.BIG_GUN);
		else if(input == 15)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.BIG_GUN);
		CommonUtil.printPlayersDetail(playerData);
	}

	public static void fireShortGun(Player playerData, int input) {
		if(input == 14)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.SHORT_GUN);
		else if(input == 14)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.SHORT_GUN);
		CommonUtil.printPlayersDetail(playerData);
	}

	public static void exitGame() {
		System.out.println("EXIT SUCCESSFULLY.");
		System.exit(0);
	}

	public static void boostEnergy(Player playerData, int input) {
		if(input == 4) {
			playerData.setpHealth(playerData.getpHealth() + IConstants.BoostEnergy.BY_DRINK);
			playerData.setpEnergyDrink(playerData.getpEnergyDrink() - 1);
		} else if(input == 8){
			playerData.seteHealth(playerData.geteHealth() + IConstants.BoostEnergy.BY_DRINK);
			playerData.seteEnergyDrink(playerData.geteEnergyDrink() - 1);
		}
		CommonUtil.printPlayersDetail(playerData);
	}

	public static void hitPunch(Player playerData, int input) {
		if(input == 1)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.PUNCH);
		else if(input == 5)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.PUNCH);
		CommonUtil.printPlayersDetail(playerData);
	}

	public static void hitKick(Player playerData, int input) {
		if(input == 2)
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.KICK);
		else if(input == 6)
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.KICK);
		CommonUtil.printPlayersDetail(playerData);
	}
	
	public static void explorePlayer(Scanner scan, Player p1, int input) {
		boolean isExplored = true;
		while (isExplored) {
			System.out.println("What do you want to buy?");
			System.out.println("1. Energy Drink.");
			System.out.println("2. short Gun [Cost : 100 Gold]");
			System.out.println("3. big Gun [Cost : 200 Gold]");
			System.out.println("4. Don't want to Buy Anything.");
			int n = scan.nextInt();
			if (n == 1 && input == 3) {
				System.out.println("How Many Drink you want to buy ("
						+ IConstants.Price.ENERGY_DRINNK + " gold per piece) : ");
				int drinkCount = scan.nextInt();
				int totalPrice = drinkCount * IConstants.Price.ENERGY_DRINNK;
				if (p1.getpGold() >= totalPrice) {
					System.out.println("Purchased successfully.");
					p1.setpGold(p1.getpGold() - totalPrice);
					p1.setpEnergyDrink(p1.getpEnergyDrink() + drinkCount);
					CommonUtil.printPlayersDetail(p1);
					isExplored = false;
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 2 && input == 3) {
				if (p1.getpGold() >= IConstants.Price.SHORT_GUN) {
					if (p1.getpShortGun() == 0) {
						p1.setpGold(p1.getpGold() - IConstants.Price.SHORT_GUN);
						p1.setpShortGun((byte)1);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Short Gun.");
					}
					CommonUtil.printPlayersDetail(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 3 && input == 3) {
				if (p1.getpGold() >= IConstants.Price.BIG_GUN) {
					if (p1.getpBigGun() == 0) {
						p1.setpGold(p1.getpGold() - IConstants.Price.BIG_GUN);
						p1.setpBigGun((byte)1);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Big Gun.");
					}
					CommonUtil.printPlayersDetail(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			}else if (n == 1 && input == 7) {
				System.out.println("How Many Drink you want to buy ("
						+ IConstants.Price.ENERGY_DRINNK + " gold per piece) : ");
				int drinkCount = scan.nextInt();
				int totalPrice = drinkCount * IConstants.Price.ENERGY_DRINNK;
				if (p1.geteGold() >= totalPrice) {
					System.out.println("Purchased successfully.");
					p1.seteGold(p1.geteGold() - totalPrice);
					p1.seteEnergyDrink(p1.geteEnergyDrink() + drinkCount);
					CommonUtil.printPlayersDetail(p1);
					isExplored = false;
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 2 && input == 7) {
				if (p1.geteGold() >= IConstants.Price.SHORT_GUN) {
					if (p1.geteShortGun() == 0) {
						p1.seteGold(p1.geteGold() - IConstants.Price.SHORT_GUN);
						p1.seteShortGun((byte)1);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Short Gun.");
					}
					CommonUtil.printPlayersDetail(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 3 && input == 7) {
				if (p1.geteGold() >= IConstants.Price.BIG_GUN) {
					if (p1.geteBigGun() == 0) {
						p1.seteGold(p1.geteGold() - IConstants.Price.BIG_GUN);
						p1.seteBigGun((byte)1);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Big Gun.");
					}
					CommonUtil.printPlayersDetail(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 4) {
				isExplored = false;
				CommonUtil.printPlayersDetail(p1);
			}
		}
	}
	
	public static boolean loadGame() {
		
		boolean isSuccess = true;
		try {
			Scanner scan = new Scanner(System.in);
			Player p1;
			Set<Player> listData = ReadWrite.readData();
			Map<Integer, Player> mapOfSavedGames = new LinkedHashMap<>();
			int count = 1;
			System.out.println("Select Number to Load Game : ");
			for (Player player : listData) {
				mapOfSavedGames.put(count, player);
				System.out.println(count + " : " + player.getGameName());
				count++;
			}
			int selectedCount = scan.nextInt();
			Player player = mapOfSavedGames.get(selectedCount);
			p1 = player;
			System.out.println("saved Game Start...");
			CommonUtil.printPlayersDetail(p1);
			boolean isPlay = true;
			// repeat action
			while (isPlay) {
				isPlay = performActions(scan, p1, isPlay);
			}
		} catch (Exception e) {
			System.out.println("EXIT DUE TO : " + e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public static boolean performActions(Scanner scan, Player playerData,
			boolean isPlay) {
		CommonUtil.printActions(playerData);
		int input = scan.nextInt();
		if (input == 1 || input == 5) {
			CommonUtil.hitPunch(playerData, input);
		} else if (input == 2 || input == 6) {
			CommonUtil.hitKick(playerData, input);
		} else if (input == 3 || input == 7) {
			CommonUtil.explorePlayer(scan, playerData, input);
		} else if (input == 4 || input == 8) {
			CommonUtil.boostEnergy(playerData, input);
		} else if (input == 9) {
			saveGame(scan, playerData);
			isPlay = false;
		} else if (input == 10) {
			loadGame();
		} else if (input == 11) {
			CommonUtil.exitGame();
		} else if (input == 12 || input == 14) {
			CommonUtil.fireShortGun(playerData, input);
		} else if (input == 13 || input == 15) {
			CommonUtil.fireBigGun(playerData, input);
		}  
		return decideWinnerAndContinue(scan, playerData, isPlay);
	}

	private static boolean decideWinnerAndContinue(Scanner scan,
			Player playerData, boolean isPlay) {
		 
		if (playerData.getpHealth() <= 0 || playerData.geteHealth() <= 0) {
			if (playerData.geteHealth() < playerData.getpHealth()) {
				System.out.println(playerData.getpName().toUpperCase()
						+ " IS WINNER.");
				playerData.seteHealth(0);
				playerData.setpGold(playerData.getpGold() + IConstants.Bonus.GOLD);
			} else {
				System.out.println(playerData.geteName().toUpperCase()
						+ " IS WINNER.");
				playerData.setpHealth(0);
				playerData.seteGold(playerData.geteGold() + IConstants.Bonus.GOLD);
			}
			System.out.println("GAME FINISHED.");
			System.out.println("Do you want to Continue The Game? ");
			System.out.println("1. YES");
			System.out.println("2. NO");
			int value = scan.nextInt();
			if (value == 1) {
				while (isPlay) {
					if (playerData.geteHealth() <= 0) {
						playerData.seteHealth(100);
						playerData.setpHealth(playerData.getpHealth() + 100);
					} else if (playerData.getpHealth() <= 0) {
						playerData.setpHealth(100);
						playerData.seteHealth(playerData.geteHealth() + 100);
					}
					CommonUtil.printPlayersDetail(playerData);
					isPlay = performActions(scan, playerData, isPlay);
				}
			} else if (value == 2) {
				System.exit(0);
			}
		}
		return isPlay;
	}

	private static void saveGame(Scanner scan, Player playerData) {
		try {
			System.out.println("Enter Game Name Without space : ");
			String gameName = scan.next();
			playerData.setGameName(gameName);
			ReadWrite.writeData(playerData);
			System.out.println("Game saved Successfully and Exit");
		} catch (IOException e) {
			System.out.println("EXIT DUE TO : " + e.getMessage());
			System.exit(0);
		}
	}

	public static void printDashLine(){
		System.out.println("=================================================");
	}
}
