package com.olliepugh.yetanothergrapplinghook;


import org.bukkit.plugin.java.JavaPlugin;

import com.olliepugh.yetanothergrapplinghook.commands.Gh;
import com.olliepugh.yetanothergrapplinghook.listeners.FishingHookListener;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() { // when the plugin is enabled
		new Gh(this);
		new FishingHookListener(this);
		loadConfig();
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
