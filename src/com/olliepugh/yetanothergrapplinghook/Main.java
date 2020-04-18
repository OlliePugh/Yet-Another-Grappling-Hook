package com.olliepugh.yetanothergrapplinghook;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olliepugh.yetanothergrapplinghook.commands.Gh;
import com.olliepugh.yetanothergrapplinghook.listeners.FallDamageListener;
import com.olliepugh.yetanothergrapplinghook.listeners.FishingHookListener;

public class Main extends JavaPlugin{
	
	public List<Player> grapplingPlayers; // stores all the players that are currently airborn and grappling
	public Map<Player, Long> lastGrapple; // stores a player with a reference to their last time grappling
	
	@Override
	public void onEnable() { // when the plugin is enabled
		new Gh(this);
		new FishingHookListener(this);
		new FallDamageListener(this);
		loadConfig();
		grapplingPlayers = new ArrayList<Player>();
		lastGrapple = new HashMap<Player, Long>();
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
