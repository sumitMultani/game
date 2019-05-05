package com.example.coding.service.impl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.coding.model.Player;
import com.example.coding.service.FightGameService;
import com.example.coding.util.IConstants;
import com.example.coding.util.ReadWrite;

@Service
public class FightGameServiceImpl implements FightGameService {

	@Override
	public void startGame() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1. NEW GAME");
		System.out.println("2. LOAD GAME");
		System.out.println("(Select 1 or 2)");
		int startPoint = scan.nextInt();
		if (startPoint == 1) {
			startNewGame(scan);
		} else if (startPoint == 2) {
			loadGame2(scan);
		}

	}

	private static void startNewGame(Scanner scan) {
		Player p1;
		System.out.println("======***==Create a Character===***=======");
		System.out.println("Enter Player1 Name : ");
		String p1Name = scan.next();
		System.out.println("Enter Player2 Name : ");
		String p2Name = scan.next();

		p1 = new Player(p1Name, 100, 1000l, 5l, 0l, 0l, p2Name, 100, 1000l, 5l,
				0l, 0l);
		// System.out.println(p1);
		System.out
				.println("=======================START FIGHT===============================");
		System.out.println("***********************  "
				+ p1.getpName().toUpperCase() + " vs "
				+ p1.geteName().toUpperCase() + "  ******************");
		System.out
				.println("==================================================================");
		System.out.println(p1);
		boolean isPlay = true;
		// repeat action
		while (isPlay) {
			isPlay = performActions(scan, p1, isPlay);
		}
	}

	private static boolean performActions(Scanner scan, Player playerData,
			boolean isPlay) {
		printActions(playerData);
		int input = scan.nextInt();
		if (input == 1) {
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.PUNCH);
			System.out.println(playerData);
		} else if (input == 2) {
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.KICK);
			System.out.println(playerData);
		} else if (input == 3) {
			explorePlayer1(scan, playerData);
		} else if (input == 4) {
			playerData.setpHealth(playerData.getpHealth() + IConstants.BoostEnergy.BY_DRINK);
			playerData.setpEnergyDrink(playerData.getpEnergyDrink() - 1);
			System.out.println(playerData);
		} else if (input == 5) {
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.PUNCH);
			System.out.println(playerData);
		} else if (input == 6) {
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.KICK);
			System.out.println(playerData);
		} else if (input == 7) {
			explorePlayer2(scan, playerData);
		} else if (input == 8) {
			playerData.seteHealth(playerData.geteHealth() + IConstants.BoostEnergy.BY_DRINK);
			playerData.seteEnergyDrink(playerData.geteEnergyDrink() - 1);
			System.out.println(playerData);
		} else if (input == 9) {
			isPlay = saveGame(scan, playerData);
		} else if (input == 10) {
			playerData = loadGame(scan, playerData);
		} else if (input == 11) {
			System.out.println("EXIT SUCCESSFULLY.");
			System.exit(0);
		} else if (input == 12) {
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.SHORT_GUN);
			System.out.println(playerData);
		} else if (input == 13) {
			playerData.seteHealth(playerData.geteHealth() - IConstants.Attack.BIG_GUN);
			System.out.println(playerData);
		} else if (input == 14) {
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.SHORT_GUN);
			System.out.println(playerData);
		} else if (input == 15) {
			playerData.setpHealth(playerData.getpHealth() - IConstants.Attack.BIG_GUN);
			System.out.println(playerData);
		}

		// compare champion.
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
					System.out.println(playerData);
					isPlay = performActions(scan, playerData, isPlay);
				}
			} else if (value == 2) {
				System.exit(0);
			}
		}
		return isPlay;
	}

	private static void loadGame2(Scanner scan) {
		Player p1;
		try {
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
			System.out.println(p1);
			boolean isPlay = true;
			// repeat action
			while (isPlay) {
				isPlay = performActions(scan, p1, isPlay);
			}
		} catch (Exception e) {
			System.out.println("EXIT DUE TO : " + e.getMessage());
			System.exit(0);
		}
	}

	private static Player loadGame(Scanner scan, Player playerData) {
		try {
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
			playerData = player;
			System.out.println("saved Game Start...");
			System.out.println(playerData);
		} catch (Exception e) {
			System.out.println("EXIT DUE TO : " + e.getMessage());
			System.exit(0);
		}
		return playerData;
	}

	private static boolean saveGame(Scanner scan, Player playerData) {
		boolean isPlay;
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
		isPlay = false;
		return isPlay;
	}

	private static void explorePlayer2(Scanner scan, Player p1) {
		boolean isExplored = true;
		while (isExplored) {
			System.out.println("What do you want to buy?");
			System.out.println("1. Energy Drink.");
			System.out.println("2. short Gun [Cost : 100 Gold]");
			System.out.println("3. big Gun [Cost : 200 Gold]");
			System.out.println("4. Don't want to Buy Anything.");
			int n = scan.nextInt();
			if (n == 1) {
				System.out.println("How Many Drink you want to buy ("
						+ IConstants.Price.ENERGY_DRINNK + " gold per piece) : ");
				int drinkCount = scan.nextInt();
				int totalPrice = drinkCount * IConstants.Price.ENERGY_DRINNK;
				if (p1.geteGold() >= totalPrice) {
					System.out.println("Purchased successfully.");
					p1.seteGold(p1.geteGold() - totalPrice);
					p1.seteEnergyDrink(p1.geteEnergyDrink() + drinkCount);
					System.out.println(p1);
					isExplored = false;
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 2) {
				if (p1.geteGold() >= IConstants.Price.SHORT_GUN) {
					if (p1.geteShortGun() == 0) {
						p1.seteGold(p1.geteGold() - IConstants.Price.SHORT_GUN);
						p1.seteShortGun(1l);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Short Gun.");
					}
					System.out.println(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 3) {
				if (p1.geteGold() >= IConstants.Price.BIG_GUN) {
					if (p1.geteBigGun() == 0) {
						p1.seteGold(p1.geteGold() - IConstants.Price.BIG_GUN);
						p1.seteBigGun(1l);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Big Gun.");
					}
					System.out.println(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 4) {
				isExplored = false;
				System.out.println(p1);
			}
		}
	}

	private static void explorePlayer1(Scanner scan, Player p1) {
		boolean isExplored = true;
		while (isExplored) {
			System.out.println("What do you want to buy?");
			System.out.println("1. Energy Drink.");
			System.out.println("2. short Gun [Cost : 100 Gold]");
			System.out.println("3. big Gun [Cost : 200 Gold]");
			System.out.println("4. Don't want to Buy Anything.");
			int n = scan.nextInt();
			if (n == 1) {
				System.out.println("How Many Drink you want to buy ("
						+ IConstants.Price.ENERGY_DRINNK + " gold per piece) : ");
				int drinkCount = scan.nextInt();
				int totalPrice = drinkCount * IConstants.Price.ENERGY_DRINNK;
				if (p1.getpGold() >= totalPrice) {
					System.out.println("Purchased successfully.");
					p1.setpGold(p1.getpGold() - totalPrice);
					p1.setpEnergyDrink(p1.getpEnergyDrink() + drinkCount);
					System.out.println(p1);
					isExplored = false;
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 2) {
				if (p1.getpGold() >= IConstants.Price.SHORT_GUN) {
					if (p1.getpShortGun() == 0) {
						p1.setpGold(p1.getpGold() - IConstants.Price.SHORT_GUN);
						p1.setpShortGun(1l);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Short Gun.");
					}
					System.out.println(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 3) {
				if (p1.getpGold() >= IConstants.Price.BIG_GUN) {
					if (p1.getpBigGun() == 0) {
						p1.setpGold(p1.getpGold() - IConstants.Price.BIG_GUN);
						p1.setpBigGun(1l);
						isExplored = false;
					} else {
						System.out.println("You Have Already A Big Gun.");
					}
					System.out.println(p1);
				} else
					System.out
							.println("You don't have enough money to purchase.");
			} else if (n == 4) {
				isExplored = false;
				System.out.println(p1);
			}
		}
	}

	private static void printActions(Player p1) {
		System.out.println("Points :");
		System.out
				.println("[Punch = -5, Kick = -10, Explore = Buy Products, Energy = +50]");
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

}
