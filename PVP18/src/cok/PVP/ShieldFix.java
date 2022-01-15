package cok.PVP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ShieldFix extends BukkitRunnable {
	
	@Override
	public void run() {
		
		
		
		for(Player player : Bukkit.getOnlinePlayers()) {

			for(ItemStack item : player.getInventory().getContents()) {
				
				if(item != null) {
				
					if(item.getType().equals(Material.SHIELD)) {
						
						player.sendMessage(ChatColor.ITALIC +"[RustedSword] You can't use shield right now");
						item.setType(Material.IRON_INGOT);
						
						

						
						
					}
					
				
				}
				
				
				
			}
			
		}
		
	}
	}
