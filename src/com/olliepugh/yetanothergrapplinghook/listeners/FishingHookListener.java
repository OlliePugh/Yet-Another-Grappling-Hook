package com.olliepugh.yetanothergrapplinghook.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.olliepugh.yetanothergrapplinghook.Main;

public class FishingHookListener implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;

	public FishingHookListener(Main plugin) {
		FishingHookListener.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin); // tell the plugin to listen for this event
		
	}
	
	@EventHandler
	public void OnFish(PlayerFishEvent event) {
		ItemMeta inMainHandMeta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
		if (event.getState().equals(PlayerFishEvent.State.REEL_IN) && (inMainHandMeta.getDisplayName().equalsIgnoreCase("grappling hook") && inMainHandMeta.isUnbreakable())) { // if the player is reeling in 
			Player player = event.getPlayer();
			List<Entity> nearby = player.getNearbyEntities(50,50,50); // searches in a 100*100*100 radius around the player for other entities
			Entity hook = null; // holds the future hook
			for (Entity e : nearby) { // loop through entities
			    if (e.getType() == EntityType.FISHING_HOOK) { // it is a hook!
			        hook = e;
			        break;
			    }
			}
			if (hook != null) {
			    Location hookLocation = hook.getLocation(); // the location of the hook
			    Location playerLocation = player.getLocation();
			    Vector newVelocity = (hookLocation.toVector().subtract(playerLocation.toVector())).normalize().multiply(plugin.getConfig().getDouble("hook-strength"));
			    player.setVelocity(newVelocity);
			    if (!plugin.grapplingPlayers.contains(player)){
			    	plugin.grapplingPlayers.add(player);
			    }
			    
			} 
		}
	}
	
}
