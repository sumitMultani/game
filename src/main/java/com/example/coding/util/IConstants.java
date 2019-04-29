package com.example.coding.util;

public class IConstants {

	public static enum CHOICEP1 {
		BONDP1, SAADP1, NOTHING
	};

	public static enum CHOICEP2 {
		BONDP2, SAADP2, NOTHING2
	};

	public static enum STATE {
		MENU, CHARSEL1, CHARSEL2, CHOOSE, GAME
	};

	public interface gameWindows {
		public static final int WIDTH = 790;
		public static final int HEIGHT = 590;
		public static final int SCALE = 1;
		public final String TITLE = "Final Battle.";
	}

}
