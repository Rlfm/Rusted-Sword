package cok.PVP;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RodFIx extends BukkitRunnable {
	
	public List<World> wrld = Bukkit.getWorlds();
	
	public HashMap<Entity,UUID> hooks = new HashMap<Entity,UUID>();
	public HashMap<Entity,Vector>  vects = new HashMap<Entity,Vector>();

	public Vector vect;

	@Override
	public void run() {
		
		for(World w : wrld ) {

			for(Entity ent : w.getEntities()) {
				
				if(ent.getType() == EntityType.FISHING_HOOK ) {
					
					for(Entity ent2 : ent.getNearbyEntities(0.5,0.5,0.5)) {	
						
						if(ent2 instanceof LivingEntity) {
														
							if(hooks.containsKey(ent) == false & ent2.getType() == EntityType.PLAYER ) {
									
								Player player = Bukkit.getPlayer(ent2.getUniqueId());
								
								vect = player.getFacing().getDirection();
							
								vect.setY(0.3);
								vect.setX(vect.getBlockX()*0.3);
								vect.setZ(vect.getBlockZ()*0.3);
							
								hooks.put(ent, player.getUniqueId());
								vects.put(ent, vect);
							
						
							}
							else if(hooks.containsKey(ent) & ent2.getUniqueId() != hooks.get(ent) & ent2.isDead()==false){
								
																				
								vect = vects.get(ent);
								LivingEntity FinalEnt = (LivingEntity) ent2;
								
								FinalEnt.setVelocity(vect);
								FinalEnt.damage(0.5);	
								
								
								
								// Getting rid of all fishing hooks
								
								HashMap<Entity,UUID> hooks2 = new HashMap<Entity,UUID>();
								
								for(Entity hook : hooks.keySet()) {
									
									try {
										hook.getLocation();
										
									}catch(Exception e){
										
										hooks2.remove(hook,hooks2.get(hook));
										vects.remove(hook,vects.get(hook));
										
									}
								}
								
								hooks = hooks2;
	
							}
							}
						
					}
					
				
				
			}
			
		}
		}

		
	}
	
}
