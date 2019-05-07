package com.example.coding.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.example.coding.model.Player;

public class ReadWrite {

	private static Logger _log = Logger.getLogger(ReadWrite.class);

	public static void writeData(Player p) throws IOException {
		String textToAppend = getJson(p);
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				IConstants.File.NAME, true));
		writer.newLine();
		writer.write(textToAppend);
		writer.close();
	}

	public static Set<Player> readData() {
		File file = null;
		FileReader reader = null;
		String ob = "";
		Set<Player> list = new LinkedHashSet<>();
		try {
			file = new File(IConstants.File.NAME);
			reader = new FileReader(file);
			int i;
			while ((i = reader.read()) != -1) {
				if ((char) i == '\n')
					ob = "";
				else {
					ob = ob + String.valueOf((char) i);
					if ((char) i == '}') {
						ObjectMapper mapper = new ObjectMapper();
						Player ph = mapper.readValue(ob, Player.class);
						list.add(ph);

					}
				}
			}
		} catch (Exception e) {
			_log.error(e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				_log.error(e.getMessage());
			}
		}
		return list;
	}

	 

	public static String getJson(Player p) {
		return "{ \"pName\" : " + "\"" + p.getpName() + "\""
				+ " ,\"pHealth\" : " + p.getpHealth() + " ,\"pGold\" : "
				+ p.getpGold() + ",\"pEnergyDrink\" : " + p.getpEnergyDrink()
				+ " ,\"pShortGun\" : " + p.getpShortGun() + " ,\"pBigGun\" : "
				+ p.getpBigGun() + ",\"eName\" : " + "\"" + p.geteName() + "\""
				+ " ,\"eHealth\" : " + p.geteHealth() + " ,\"eGold\" : "
				+ p.geteGold() + ",\"eEnergyDrink\" : " + p.geteEnergyDrink()
				+ " ,\"eShortGun\" : " + p.geteShortGun() + " ,\"eBigGun\" : "
				+ p.geteBigGun() + ",\"gameName\" : " + "\"" + p.getGameName()
				+ "\"" + "}";
	}
}
