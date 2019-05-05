package com.example.coding.util;

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
				System.out.println("===========================");		
				System.out.println("WELCOME! GAME SELECTION...");		
				System.out.println("1. Start a New Game\n2. Start a Load Game\n3. Save Game\n4. Quit Game");		
				System.out.println("===========================");
	}
	public static void printPlayerName(Player p1) {
		System.out
				.println("=======================START FIGHT===============================");
		System.out.println("***********************  "
				+ p1.getpName().toUpperCase() + " vs "
				+ p1.geteName().toUpperCase() + "  ******************");
		System.out
				.println("==================================================================");
	}
}
