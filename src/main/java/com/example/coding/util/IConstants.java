package com.example.coding.util;

public interface IConstants {

	interface Bonus {
		Integer GOLD = 500;
	}

	interface BoostEnergy {
		Integer BY_DRINK = 50;
	}

	interface Attack {
		Integer PUNCH = 5;
		Integer KICK = 10;
		Integer SHORT_GUN = 15;
		Integer BIG_GUN = 25;
	}

	interface Price {
		Integer ENERGY_DRINNK = 50;
		Integer SHORT_GUN = 100;
		Integer BIG_GUN = 200;
	}

	interface Default {
		Integer HEALTH = 100;
		Long GOLD = 1000l;
		Integer ENERGY_DRINK = 5;
		Byte SHORT_GUN = 0;
		Byte BIG_GUN = 0;
	}

	interface Response {
		String SUCCESS = "GAME FINISHED SUCCESSFULLY.";
		String FAIL = "GAME FAILED DUE TO MISMATCH VALUE.";
	}
}
