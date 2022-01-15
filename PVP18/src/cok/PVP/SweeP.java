package cok.PVP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.ItemStack;

public class SweeP extends BukkitRunnable {
	
	@Override
	public void run() {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			
			for(ItemStack it : p.getInventory().getContents()){
				
				if(it != null) {
					
					if(it.getItemMeta().hasEnchants()) {
						
						if(it.getEnchantments().containsKey(Enchantment.SWEEPING_EDGE)) {
							
							it.removeEnchantment(Enchantment.SWEEPING_EDGE);
							p.sendMessage(ChatColor.ITALIC + "[RustedSword] Sweeping_Edge is currently disabled ");
						}
						
					}
				}
		
				
			}
			
			
		}
		
	}
	

}
