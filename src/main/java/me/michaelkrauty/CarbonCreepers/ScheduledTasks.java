package me.michaelkrauty.CarbonCreepers;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Created on 6/25/2014.
 *
 * @author michaelkrauty
 */
public class ScheduledTasks {

	private Main main;

	public ScheduledTasks(Main instance) {
		main = instance;
	}

	public void check() {
		if (main.getDataFile().getKeys("") != null) {

			for (String key : main.getDataFile().getKeys("")) {

				String[] epicenter = key.split("\\.")[0].split(",");

				if (main.getDataFile().getString(key + ".rebuild") != null) {
					int rebuild = main.getDataFile().getInt(key + ".rebuild");
					if (rebuild == 0) {

						for (String key1 : main.getDataFile().getKeys(key + ".blocks")) {

							String[] key2 = key1.split(",");

							Block block = main.getServer().getWorld(key2[0]).getBlockAt(Integer.parseInt(key2[1]), Integer.parseInt(key2[2]), Integer.parseInt(key2[3].split("\\.")[0]));

							if(main.getDataFile().getString(key + ".blocks." + key1 + ".id") != null ) {

								if (block.getType() == Material.AIR) {
									block.setType(Material.getMaterial(main.getDataFile().getString(key + ".blocks." + key1 + ".id")));
									if (main.getDataFile().getInt(key + ".blocks." + key1 + ".data") != 0) {
										block.setData(main.getDataFile().getByte(key + ".blocks." + key1 + ".data"));
									}
								}
							}
						}
						main.getDataFile().delete(key);
					} else {
						if (!(rebuild < 0)) {
							main.getDataFile().set(key + ".rebuild", rebuild - 1);
						}
					}
				}
			}
		}
	}
}
