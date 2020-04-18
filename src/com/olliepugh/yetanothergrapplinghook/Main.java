package com.olliepugh.yetanothergrapplinghook;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olliepugh.yetanothergrapplinghook.commands.Gh;
import com.olliepugh.yetanothergrapplinghook.listeners.FallDamageListener;
import com.olliepugh.yetanothergrapplinghook.listeners.FishingHookListener;

public class Main extends JavaPlugin{
	
	public List<Player> grapplingPlayers;
	
	@Override
	public void onEnable() { // when the plugin is enabled
		new Gh(this);
		new FishingHookListener(this);
		new FallDamageListener(this);
		loadConfig();
		grapplingPlayers = new ArrayList<Player>();
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
