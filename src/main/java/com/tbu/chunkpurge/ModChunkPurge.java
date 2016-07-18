package com.tbu.chunkpurge;

import org.apache.logging.log4j.Logger;

import com.tbu.chunkpurge.commands.CommandChunkPurge;
import com.tbu.chunkpurge.events.HandlerWorldTick;
import com.tbu.chunkpurge.operators.ConfigHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModChunkPurge.MODID, name = ModChunkPurge.NAME, version = ModChunkPurge.VERSION, acceptableRemoteVersions = "*")
public class ModChunkPurge {

	public static final String MODID = "ChunkPurge";
	public static final String NAME = "Chunk Purge";
	// This has to be changed each time the jar is updated to a new version
	// it seems like there could be an easier way to do this.
	public static final String VERSION = "2.1";

	@Instance(MODID)
	public static ModChunkPurge instance;

	public static Logger log;

	@EventHandler
	public void load(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new HandlerWorldTick());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		ConfigHandler.init(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandChunkPurge());
	}

}
