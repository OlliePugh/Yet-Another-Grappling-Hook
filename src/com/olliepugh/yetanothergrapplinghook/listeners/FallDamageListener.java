package com.olliepugh.yetanothergrapplinghook.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.olliepugh.yetanothergrapplinghook.Main;

public class FallDamageListener implements Listener {

	private static Main plugin;
	
	public FallDamageListener(Main plugin) {
		FallDamageListener.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin); // tell the plugin to listen for this event
	}
	
	@EventHandler (priority = EventPriority.HIGH)
    public void onEntityDamageEvent(final EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (event.getCause() == DamageCause.FALL && plugin.grapplingPlayers.contains(player)) {
        	event.setCancelled(true); // cancel the normal damage
        	double normalDamage = ((player.getFallDistance() * 0.5) - 1.5);
        	player.setFallDistance(0);
        	player.damage(normalDamage * plugin.getConfig().getDouble("damage-reduction"));
        	plugin.grapplingPlayers.remove(player);
        }
	}
}
