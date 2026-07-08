package com.mahghuuuls.baublecatalog;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:baubles"
)
public class BaubleCatalogMod {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    private static File configDirectory;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDirectory = event.getModConfigurationDirectory();
        LOGGER.info("{} initialized.", Tags.MOD_NAME);
    }

    public static File getConfigDirectory() {
        return configDirectory;
    }
}
