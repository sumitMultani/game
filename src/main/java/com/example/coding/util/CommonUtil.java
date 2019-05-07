package com.example.coding.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.coding.model.Player;

@Component
public class CommonUtil {

	private static Logger _log = Logger.getLogger(CommonUtil.class);
	
	@Autowired
	private PlayerActions playerActions;
	
	public  void printTitle(){
		_log.info(" ________  _________  ________  _______   _______  _________        ________ ___  ________  ___  ___  _________  _______   ________          ___  ___");
		_log.info("|\\   ____\\|\\___   ___|\\   __  \\|\\  ___ \\ |\\  ___ \\|\\___   ___\\     |\\  _____|\\  \\|\\   ____\\|\\  \\|\\  \\|\\___   ___|\\  ___ \\ |\\   __  \\        |\\  \\|\\  \\ ");
		_log.info("\\ \\  \\___|\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|\\ \\   __/\\|___ \\  \\_|     \\ \\  \\__/\\ \\  \\ \\  \\___|\\ \\  \\\\\\  \\|___ \\  \\_\\ \\   __/|\\ \\  \\|\\  \\       \\ \\  \\ \\  \\  ");
		_log.info(" \\ \\_____  \\   \\ \\  \\ \\ \\   _  _\\ \\  \\_|/_\\ \\  \\_|/__  \\ \\  \\       \\ \\   __\\\\ \\  \\ \\  \\  __\\ \\   __  \\   \\ \\  \\ \\ \\  \\_|/_\\ \\   _  _\\       \\ \\  \\ \\  \\  ");
		_log.info("  \\|____|\\  \\   \\ \\  \\ \\ \\  \\\\  \\\\ \\  \\_|\\ \\ \\  \\_|\\ \\  \\ \\  \\       \\ \\  \\_| \\ \\  \\ \\  \\|\\  \\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\_|\\ \\ \\  \\\\  \\|       \\ \\  \\ \\  \\ ");
		_log.info("    ____\\_\\  \\   \\ \\__\\ \\ \\__\\\\ _\\\\ \\_______\\ \\_______\\  \\ \\__\\       \\ \\__\\   \\ \\__\\ \\_______\\ \\__\\ \\__\\   \\ \\__\\ \\ \\_______\\ \\__\\\\ _\\        \\ \\__\\ \\__\\");
		_log.info("   |\\_________\\   \\|__|  \\|__|\\|__|\\|_______|\\|_______|   \\|__|        \\|__|    \\|__|\\|_______|\\|__|\\|__|    \\|__|  \\|_______|\\|__|\\|__|        \\|__|\\|__|");
		_log.info("   \\|_________|                                                                                                                                           ");
		printDashLine();		
		_log.info("WELCOME! GAME SELECTION...");		
		_log.info("1. Start a New Game\n2. Load Game");		
		printDashLine();
		_log.info("(Select 1 or 2) : ");
	}
	
	public void printPlayersName(Player p1) {
		_log.info("=======================START FIGHT===============================");
		_log.info("***********************  "
				+ p1.getpName().toUpperCase() + " vs "
				+ p1.geteName().toUpperCase() + "  ******************");
		printDashLine();
	}
	
	public void printPlayersDetail(Player p1) {
		_log.info(p1);
	}
	
	public void printActions(Player p1) {
		printDashLine();
		_log.info("Points :");
		_log.info("[Punch = -5, Kick = -10, Explore = Buy Products, Energy = +50]");
		printDashLine();
		_log.info("What do you want to do ?");
		_log.info("1. Punch by P1");
		_log.info("2. Kick by P1");
		_log.info("3. Explore P1");
		if (p1.getpEnergyDrink() > 0) {
			_log.info("4. Boost Energy P1");
		}
		_log.info("5. Punch by P2");
		_log.info("6. Kick by P2");
		_log.info("7. Explore  P2");
		if (p1.geteEnergyDrink() > 0) {
			_log.info("8. Boost Energy P2");
		}
		_log.info("9. Save And Exit Game");
		_log.info("10. Open Saved Games");
		_log.info("11. Exit");
		if (p1.getpShortGun() == 1) {
			_log.info("12. Short Gun Fire p1");
		}
		if (p1.getpBigGun() == 1) {
			_log.info("13. Big Gun Fire p1");
		}
		if (p1.geteShortGun() == 1) {
			_log.info("14. Short Gun Fire p2");
		}
		if (p1.geteBigGun() == 1) {
			_log.info("15. Big Gun Fire p2");
		}
	}
	
	
	
	public boolean decideWinnerAndContinue(Player playerData, boolean isPlay) {
		if (playerData.getpHealth() <= 0 || playerData.geteHealth() <= 0) {
			printDashLine();
			if (playerData.geteHealth() < playerData.getpHealth()) {
				_log.info(playerData.getpName().toUpperCase()
						+ " IS WINNER.");
				playerData.seteHealth(0);
				playerData.setpGold(playerData.getpGold() + IConstants.Bonus.GOLD);
			} else {
				_log.info(playerData.geteName().toUpperCase()
						+ " IS WINNER.");
				playerData.setpHealth(0);
				playerData.seteGold(playerData.geteGold() + IConstants.Bonus.GOLD);
			}
			_log.info("GAME FINISHED.");
			printDashLine();
			_log.info("Do you want to Continue The Game? ");
			_log.info("1. YES");
			_log.info("2. NO");
			int value = scan.nextInt();
			_log.info("(Select 1 or 2)");
			_log.info("user input : "+value);
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
		_log.info("=================================================");
	}
	
	Scanner scan = new Scanner(System.in);
	public List<String> getPlayerNames() {
		List<String> names = new ArrayList<>();
		_log.info("======***==Create Characters By Name===***=======");
		_log.info("Enter Player1 Name : ");
		String name1 = scan.next();
		names.add(name1);
		_log.info("Enter Player2 Name : ");
		String name2 =scan.next();
		names.add(name2);	
		return names;
	}
	
	public static String getFilePath(String fileName) {
		String path = "";
		try{
			File file = new ClassPathResource(fileName).getFile();
			path = file.getPath();
		}catch(Exception e){
			System.out.println(e);
		}
		return path;
	}
}
