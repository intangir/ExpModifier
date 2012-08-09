package org.intangir.ExpModifier;

//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExpListener implements Listener {

	// change xp yield from bottle
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onExpBottleEvent(ExpBottleEvent e) {
		e.setExperience(25); // this is the existing bottle'o setting
	}

	// reduce all incoming Xp by 1/5
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerExpChangeEvent(PlayerExpChangeEvent e) {
		e.setAmount((int) Math.floor((double)e.getAmount() / 5)); 
		//Player p = e.getPlayer();
		//p.sendMessage(ChatColor.AQUA + "you received " + e.getAmount() + " experience.");
	}
}
