package com.spectrasonic.accessportal;

import lombok.Getter;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class Main extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        registerCommands();
        registerEvents();
        getServer().getConsoleSender().sendMessage("§dPortalAccess §aPlugin enabled");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§dPortalAccess §cPlugin disabled");
        saveConfig();
    }

    public void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void registerCommands() {
        PaperCommandManager portalCommands = new co.aikar.commands.PaperCommandManager(this);
        portalCommands.registerCommand(new PortalCommand(this));

    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PortalListener(this), this);

    }

}


