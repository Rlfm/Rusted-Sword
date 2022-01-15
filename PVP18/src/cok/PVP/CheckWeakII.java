package cok.PVP;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckWeakII extends BukkitRunnable{
	
	public void run() {
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			AttributeInstance pit = p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
			
			if(p.hasPotionEffect(PotionEffectType.WEAKNESS) & pit.getBaseValue()<10) {
				
				if(p.getPotionEffect(PotionEffectType.WEAKNESS).getAmplifier() > 0) {
					
					pit.setBaseValue(10);
					
				}
			}
		}
	}

}
