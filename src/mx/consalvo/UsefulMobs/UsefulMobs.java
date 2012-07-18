package mx.consalvo.UsefulMobs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mx.consalvo.UsefulMobs.listeners.UsefulMobsEntityListener;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class UsefulMobs extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");

	public static List<Egg> eggs = new ArrayList<Egg>();
	public static Economy economy = null;

	public void onDisable() {
		log.info(this.getDescription().getName() + " v"
				+ this.getDescription().getVersion() + " is disabled!");
	}

	public void onEnable() {
		this.CheckConfigurationFile();
		PluginManager pm = this.getServer().getPluginManager();
		log.info(this.getDescription().getName() + " v"
				+ this.getDescription().getVersion() + " is enabled!");

		final UsefulMobsEntityListener entityListener = new UsefulMobsEntityListener(this);

		pm.registerEvents(entityListener, this);

		if (getServer().getPluginManager().getPlugin("Vault") != null) {
			RegisteredServiceProvider<Economy> economyProvider = getServer()
					.getServicesManager().getRegistration(Economy.class);
			if (economyProvider != null) {
				economy = economyProvider.getProvider();
			}
		}
	}

	public void CheckConfigurationFile() {
		double configVersion = this.getConfig().getDouble("ConfigVersion", 0.0);
		if (configVersion == 1.0) {
			//
			this.saveConfig();
		} else if (configVersion == 1.18) {
			this.getConfig().set("ConfigVersion", 1.0);
			this.saveConfig();
		} else {
			this.saveResource("config.yml", true);
			this.reloadConfig();
		}
	}
}