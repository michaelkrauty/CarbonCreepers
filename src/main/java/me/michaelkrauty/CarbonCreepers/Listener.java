package me.michaelkrauty.CarbonCreepers;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

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
		if (event.getEntityType() == EntityType.CREEPER) {
			List<Block> blocks = event.blockList();
			for (Block block : blocks) {
				main.getDataFile().set(main.locationToString(event.getLocation()) + ".blocks." + main.locationToString(block.getLocation()) + ".id",
						block.getType().toString());
				main.getDataFile().set(main.locationToString(event.getLocation()) + ".blocks." + main.locationToString(block.getLocation()) + ".data", block.getData());
			}
			main.getDataFile().set(main.locationToString(event.getLocation()) + ".rebuild", 10);
		}
	}
}
