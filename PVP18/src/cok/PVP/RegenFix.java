package cok.PVP;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RegenFix extends BukkitRunnable{

	@Override
	public void run() {
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			if(player.getHealth()< player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() & player.getFoodLevel() >=17) {
				
				player.setHealth(player.getHealth() + 1);
				
				if(player.getFoodLevel() >= 20 & player.getSaturation()>0){
					player.setSaturation(player.getSaturation()-1);
				}
				else {
					player.setFoodLevel(player.getFoodLevel()-1);
				}
				
		
				
			}
			
		}
	
		
	}

}
