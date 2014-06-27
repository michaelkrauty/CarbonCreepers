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
		final ArrayList<Object> al = new ArrayList<Object>();
			for (Block block : blocks) {
				al.add(block.getType().toString());
				al.add(Byte.toString(block.getData()));
				al.add(block.getLocation());
			}
		BukkitScheduler scheduler = Bukkit.getScheduler();
		scheduler.scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				main.repair(al);
			}
		}, 300);
	}
}
