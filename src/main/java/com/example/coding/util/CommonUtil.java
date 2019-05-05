package com.example.coding.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coding.model.Player;

@Component
public class CommonUtil {

	@Autowired
	private PlayerActions playerActions;
	
	public  void printTitle(){
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
	
	public void printPlayersName(Player p1) {
		System.out
				.println("=======================START FIGHT===============================");
		System.out.println("***********************  "
				+ p1.getpName().toUpperCase() + " vs "
				+ p1.geteName().toUpperCase() + "  ******************");
		printDashLine();
	}
	
	public void printPlayersDetail(Player p1) {
		System.out.println(p1);
	}
	
	public void printActions(Player p1) {
		printDashLine();
		System.out.println("Points :");
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
	
	
	
	public boolean decideWinnerAndContinue(Player playerData, boolean isPlay) {

		if (playerData.getpHealth() <= 0 || playerData.geteHealth() <= 0) {
			printDashLine();
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
			printDashLine();
			System.out.println("Do you want to Continue The Game? ");
			System.out.println("1. YES");
			System.out.println("2. NO");
			int value = scan.nextInt();
			System.out.println("(Select 1 or 2)");
			if (value == 1) {
				while (isPlay) {
					if (playerData.geteHealth() <= 0) {
						playerData.seteHealth(100);
						playerData.setpHealth(playerData.getpHealth() + 100);
					} else if (playerData.getpHealth() <= 0) {
						playerData.setpHealth(100);
						playerData.seteHealth(playerData.geteHealth() + 100);
					}
					printPlayersDetail(playerData);
					isPlay = playerActions.performActions(playerData, isPlay);
				}
			} else if (value == 2) {
				System.exit(0);
			}
		}
		return isPlay;
	}

	
	public void printDashLine(){
		System.out.println("=================================================");
	}
	
	Scanner scan = new Scanner(System.in);
	public List<String> getPlayerNames() {
		List<String> names = new ArrayList<>();
		System.out.println("======***==Create Characters By Name===***=======");
		System.out.println("Enter Player1 Name : ");
		String name1 = scan.next();
		names.add(name1);
		System.out.println("Enter Player2 Name : ");
		String name2 =scan.next();
		names.add(name2);	
		return names;
	}
}
