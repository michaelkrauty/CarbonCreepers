package me.michaelkrauty.CarbonCreepers;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;

/**
 * Created on 6/27/2014.
 *
 * @author michaelkrauty
 */
public class Listener implements org.bukkit.event.Listener {

	private Main main;

	public Listener(Main instance) {
		main = instance;
	}

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {

		final ArrayList<ArrayList<Object>> al = new ArrayList<ArrayList<Object>>();

		for (Block block : event.blockList()) {
			if (shouldRegen(block)) {
				ArrayList<Object> al2 = new ArrayList<Object>();
				al2.add(block.getType().toString());
				al2.add(Byte.toString(block.getData()));
				al2.add(block.getLocation());
				al.add(al2);
			}
		}

		main.regenThis.add(al);

		Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				main.repairWarning(al);
			}
		}, ((main.getConfigFile().getInt("regen_delay")) * 20) - main.getConfigFile().getInt("warning_time"));
	}

	private boolean shouldRegen(Block block) {
		return !block.getType().toString().equals("TNT")
				&& !block.getType().toString().equals("DIAMOND_BLOCK")
				&& !block.getType().toString().equals("EMERALD_BLOCK")
				&& !block.getType().toString().equals("GOLD_BLOCK")
				&& !block.getType().toString().equals("IRON_BLOCK")
				&& !block.getType().toString().equals("LAPIS_BLOCK")
				&& !block.getType().toString().equals("COAL_BLOCK")
				&& !block.getType().toString().equals("REDSTONE_BLOCK")
				&& !block.getType().toString().equals("QUARTZ_BLOCK")
				&& !block.getType().toString().equals("ENCHANTMENT_TABLE")
				&& !block.getType().toString().equals("ANVIL")
				&& !block.getType().toString().equals("BEACON")
				&& !block.getType().toString().equals("IRON_BLOCK")
				&& !block.getType().toString().equals("PISTON_BASE")
				&& !block.getType().toString().equals("PISTON_STICKY_BASE")
				&& !block.getType().toString().equals("BREWING_STAND")
				&& !block.getType().toString().equals("CHEST")
				&& !block.getType().toString().equals("TRAPPED_CHEST")
				&& !block.getType().toString().equals("FURNACE")
				&& !block.getType().toString().equals("BURNING_FURNACE")
				&& !block.getType().toString().equals("ENDER_CHEST")
				&& !block.getType().toString().equals("GLOWSTONE")
				&& !block.getType().toString().equals("COAL_ORE")
				&& !block.getType().toString().equals("DIAMOND_ORE")
				&& !block.getType().toString().equals("EMERALD_ORE")
				&& !block.getType().toString().equals("GOLD_ORE")
				&& !block.getType().toString().equals("IRON_ORE")
				&& !block.getType().toString().equals("LAPIS_LAZULI_ORE")
				&& !block.getType().toString().equals("NETHER_QUARTZ_ORE")
				&& !block.getType().toString().equals("REDSTONE_ORE");
	}
}
