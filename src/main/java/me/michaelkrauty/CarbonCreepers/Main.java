package me.michaelkrauty.CarbonCreepers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created on 6/27/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	public static Main main;

	public static Config config;
	public static DataFile dataFile;

	public void onEnable() {
		main = this;
		checkDataFolder();
		dataFile = new DataFile();
		config = new Config(this);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new me.michaelkrauty.CarbonCreepers.Listener(this), this);
	}

	public void onDisable() {
		// TODO: save blocks which still need to be regenerated
	}

	public void checkDataFolder() {
		if (!getDataFolder().exists())
			getDataFolder().mkdir();
	}

	public void repairWarning(ArrayList<ArrayList<Object>> al) {
		ArrayList<Player> sent = new ArrayList<Player>();
		int warning = getConfigFile().getInt("warning_time");
		if (getConfigFile().getBoolean("warning")) {
			for (ArrayList<Object> al2 : al) {
				final Location location = (Location) al2.get(2);
				for (Player player : main.getServer().getOnlinePlayers()) {
					if (player.getWorld() == location.getWorld()) {
						if (location.distance(player.getLocation()) <= 25) {
							if (!sent.contains(player)) {
								player.sendMessage(ChatColor.RED + "**Explosion area regenerating in " + warning + " seconds**");
								sent.add(player);
							}
						}
					}
				}
			}
		}
		final ArrayList<ArrayList<Object>> al2 = al;
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				repair(al2);
			}
		}, warning * 20);
	}

	public void repair(ArrayList<ArrayList<Object>> al) {
		ArrayList<Player> sent = new ArrayList<Player>();
		for (ArrayList<Object> al2 : al) {
			final String type = (String) al2.get(0);
			final Byte data = Byte.parseByte(((String) al2.get(1)));
			final Location location = (Location) al2.get(2);
			for (Player player : main.getServer().getOnlinePlayers()) {
				if (player.getWorld() == location.getWorld()) {
					if (location.distance(player.getLocation()) <= 25) {
						if (!sent.contains(player)) {
							player.sendMessage(ChatColor.RED + "**Explosion area regenerating**");
							sent.add(player);
						}
					}
				}
			}
			if (location.getWorld().getBlockAt(location).getType() == Material.AIR
					|| location.getWorld().getBlockAt(location).getType() == Material.FIRE) {
				location.getWorld().getBlockAt(location).setType(Material.getMaterial(type));
				location.getWorld().getBlockAt(location).setData(data);
			}
		}
	}

	public Config getConfigFile() {
		return config;
	}
}
