package com.olliepugh.yetanothergrapplinghook.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GrapplingHook extends ItemStack{
	
	public GrapplingHook() {
		super(Material.FISHING_ROD);
		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName("Grappling Hook");
		meta.setUnbreakable(true);
		List<String> lores = new ArrayList<String>();
		meta.setLore(lores);
		this.setItemMeta(meta);
	}
}
