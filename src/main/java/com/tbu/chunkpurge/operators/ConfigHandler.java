package com.tbu.chunkpurge.operators;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {

	public static Configuration config;
	
	public static int chunkUnloadDelay;
	public static boolean enabled;
	public static boolean debug;
	public static int pradius;
	public static int tradius;
	public static int sradius;
	public static String dimlist;

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);
			loadConfiguration();
		}
	}

	private static void loadConfiguration() {
		config.load();

		enabled = config.getBoolean(Configuration.CATEGORY_GENERAL, "enable", true,
				"Setting to false will prevent any attempts to unload chunks."
						+ "\nChange in game with /chunkpurge enable <true|false>" + "\nDefault: true");


		chunkUnloadDelay = config.getInt(Configuration.CATEGORY_GENERAL, "chunkUnloadDelay", 600, 1, 1000,
				"The number of ticks to wait between chunk unloading attempts. Must be an integer greater than 0."
						+ "\nChange in game with /chunkpurge delay <ticks>" + "\nDefault: 600");


		debug = config.getBoolean(Configuration.CATEGORY_GENERAL, "debug", false,
				"Logs the number of chunks unloaded from each dimension, and how long is spent calculating which chunks to unload."
						+ "\nChange in game with /chunkpurge debug <true|false>" + "\nDefault: false");


		pradius = config.getInt(Configuration.CATEGORY_GENERAL, "praduius", 4, 1, 20,
				"The number of chunks around a player outside of player view range to ignore while unloading chunks."
						+ "\nChange in game with /chunkpurge pradius <# of chunks>" + "\nDefault: 4");


		tradius = config.getInt(Configuration.CATEGORY_GENERAL, "traduius", 5, 1, 20,
				"The number of chunks around a forced chunk ticket to ignore while unloading chunks."
						+ "\nChange in game with /chunkpurge tradius <# of chunks>" + "\nDefault: 5");

		sradius = config.getInt(Configuration.CATEGORY_GENERAL, "sraduius", 3, 1, 20,
				"The number of chunks around the spawn chunks to ignore while unloading chunks."
						+ "\nChange in game with /chunkpurge tradius <# of chunks>" + "\nDefault: 3");

		dimlist = config.getString(Configuration.CATEGORY_GENERAL, "dimlist", "0",
				"A comma seperated list of dimension ID's that ChunkPurge should query and purge"
						+ "\nChange in game with /chunkpurge dimlist add OR /chunkpurge dimlist remove <dim #,dim #,ect.>"
						+ "\nExample for running ChunkPurge on Overworld, Nether, and End (0,-1,1)"
						+ "\nDefault is Overworld Only (you can add any registered dimensions from any mod)"
						+ "\nDefault: 0");

		config.save();

	}

	public static void saveConfig() {
		if (config.hasChanged()) {
			config.save();
		}
	}
}
