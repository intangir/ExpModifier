package org.intangir.ExpModifier;

//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ExpListener implements Listener {

	// reduce all incoming Xp by 1/5
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerExpChangeEvent(PlayerExpChangeEvent e) {
		e.setAmount((int) Math.floor((double)e.getAmount() / 5)); 
	}

	// change xp yield from bottle
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onExpBottleEvent(ExpBottleEvent e) {
		e.setExperience(460); // 9 potions yields 30 levels (after divide by 5 above)
	}

	public static int levelToXP(int level) {
		return (int) Math.round(level*17 + Math.max(level-16,0)*Math.max(level-15,0)*1.5 + Math.max(level-31,0)*Math.max(level-30,0)*2);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
    	if (!e.isCancelled() && e.getMaterial() == Material.GLASS_BOTTLE && e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
    		
    		e.setCancelled(true);
    		
    		Player p = e.getPlayer();
    		int levels = p.getLevel();
    		int total = levelToXP(levels) + (int) Math.round((17 + Math.max(levels-15,0)*3 + Math.max(levels-30,0)*4)*p.getExp());
    		
    		if(total < 92)
    		{
    			p.sendMessage(ChatColor.RED+"Not enough Experience to fill a bottle.");
    			return;
    		}
    		
    		// remove amount of XP from total for bottle ((460/5 = 92) * 5 = 460)
    		total -= 92;

			// remove 1 bottle from hand
    		ItemStack stack = p.getItemInHand();
			stack.setAmount(stack.getAmount() - 1);
			p.setItemInHand(stack);

    		// reset Xp to new value
    		p.setExp(0);
    		p.setLevel(0);
    		p.setTotalExperience(0);
    		p.giveExp(total);
    		
    		// try to add it to inventory
    		HashMap<Integer, ItemStack> hash = p.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE));
    		// or drop it on ground
			if (!hash.isEmpty()) {
				Iterator<Integer> it = hash.keySet().iterator();
				if (it.hasNext()) {
					p.getWorld().dropItem(p.getLocation(), hash.get(it.next()));
				}
			}
			p.sendMessage(ChatColor.AQUA+"Filled an Experience Bottle!");
    		p.updateInventory();
    		
    	}
    }
	
}




