package me.michaelkrauty.CarbonCreepers;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

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
		List<Block> blocks = event.blockList();

		final ArrayList<ArrayList<Object>> al = new ArrayList<ArrayList<Object>>();
		for (Block block : blocks) {
			if (!block.getType().toString().equals("TNT")) {
				ArrayList<Object> al2 = new ArrayList<Object>();
				al2.add(block.getType().toString());
				al2.add(Byte.toString(block.getData()));
				al2.add(block.getLocation());
				al.add(al2);
			}
		}
		BukkitScheduler scheduler = Bukkit.getScheduler();
		scheduler.scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				main.repair(al);
			}
		}, (main.getConfigFile().getInt("regen_delay")) * 20);
	}
}
