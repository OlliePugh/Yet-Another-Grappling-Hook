package com.olliepugh.yetanothergrapplinghook.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.olliepugh.yetanothergrapplinghook.Main;
import com.olliepugh.yetanothergrapplinghook.items.GrapplingHook;

public class Gh implements CommandExecutor {
	
	public static Main plugin;
	
	public Gh(Main plugin) {
		Gh.plugin = plugin;
		
		plugin.getCommand("ghgive").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (!(sender instanceof Player)) { // if the sender is a player
			sender.sendMessage("This command can only be executed by a player");
			return false;
		}
		
		if (args.length == 0) { // if on player was specified
			sender.sendMessage("Please specify the player that you are giving the grappling hook to");
			return false;
		}
		
		if (Bukkit.getServer().getPlayerExact(args[0]) == null) { // if the player is not online
			sender.sendMessage(args[0] + " is not currently online");
			return false;
		}
		
		Player giver = (Player) sender;
		Player reciever = Bukkit.getServer().getPlayer(args[0]);
		
		if (!giver.hasPermission("yetanothergrapplinghook.give")) { // if the player has permission
			giver.sendMessage("You do not have permission to use this command");
			return false;
		}
		else {
			reciever.getInventory().addItem(new GrapplingHook()); // give the player the item
			giver.sendMessage("Grappling hook added to " + reciever.getDisplayName() + "'s inventory");
			reciever.sendMessage("You have been given a grappling hook by " + giver.getDisplayName());
			return true;
		}
	}
}
