package cok.PVP;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class isOnGround extends BukkitRunnable {
	
	@Override
	public void run() {
		
		
		// For double jump purpose
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			if(player.isOnGround()) {
				player.setAllowFlight(true);
			}
			
			
			
		}
		
	}
	}
