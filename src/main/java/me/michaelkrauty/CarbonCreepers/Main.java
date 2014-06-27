package me.michaelkrauty.CarbonCreepers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/27/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	public static Main main;

	public static DataFile dataFile;

	public void onEnable() {
		main = this;
		checkDataFolder();
		dataFile = new DataFile();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new me.michaelkrauty.CarbonCreepers.Listener(this), this);
	}

	public void checkDataFolder() {
		if (!getDataFolder().exists())
			getDataFolder().mkdir();
	}

	public void repair(ArrayList<ArrayList<Object>> al) {
		for (ArrayList<Object> al2 : al) {
			String type = (String) al2.get(0);
			Byte data = Byte.parseByte(((String) al2.get(1)));
			Location location = (Location) al2.get(2);
			location.getWorld().getBlockAt(location).setType(Material.getMaterial(type));
			location.getWorld().getBlockAt(location).setData(data);
		}
	}
}
