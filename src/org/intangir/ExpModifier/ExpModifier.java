package org.intangir.ExpModifier;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ExpModifier extends JavaPlugin {

    public Logger log;
    public PluginDescriptionFile pdfFile;
    public ExpListener listener;
    
	public void onEnable() {
		log = this.getLogger();
		pdfFile = this.getDescription();
		listener = new ExpListener();
		
		Bukkit.getPluginManager().registerEvents(listener, this);
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
	}
	
	public void onDisable() {
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " disabled.");
	}
}

