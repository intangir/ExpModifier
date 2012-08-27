package org.intangir.ExpModifier;

//import java.lang.reflect.Field;
import java.util.logging.Logger;

//import net.minecraft.server.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
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

		//add recipes
		ShapelessRecipe expToEmeraldRecipe = new ShapelessRecipe(new ItemStack(Material.EMERALD,1));
		//for (int i = 0; i < 3; i++) {
			expToEmeraldRecipe.addIngredient(Material.EXP_BOTTLE);
		//}
		Bukkit.addRecipe(expToEmeraldRecipe);
		ShapelessRecipe emeraldToExpRecipe = new ShapelessRecipe(new ItemStack(Material.EXP_BOTTLE,1));
		emeraldToExpRecipe.addIngredient(Material.EMERALD);
		Bukkit.addRecipe(emeraldToExpRecipe);

		/*try {
			Field field=Item.class.getDeclaredField("maxStackSize");
			field.setAccessible(true);
			field.setInt(Item.EXP_BOTTLE, 9);
		}
		catch (Exception e) {}*/
		
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
	}
	
	public void onDisable() {
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " disabled.");
	}
}

