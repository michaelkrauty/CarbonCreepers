package me.michaelkrauty.CarbonCreepers;

import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created on 6/27/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	public static Main main;

	public static DataFile dataFile;

	public static ScheduledTasks scheduledTasks;

	public void onEnable() {
		main = this;
		checkDataFolder();
		dataFile = new DataFile();
		scheduledTasks = new ScheduledTasks(this);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new me.michaelkrauty.CarbonCreepers.Listener(this), this);
		final BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				scheduledTasks.check();
			}
		}, 0L, 1200L);
	}

	public void checkDataFolder() {
		if (!getDataFolder().exists())
			getDataFolder().mkdir();
	}

	public DataFile getDataFile() {
		return dataFile;
	}

	public String locationToString(Location loc) {
		String world = loc.getWorld().getName();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		return world + "," + x + "," + y + "," + z;
	}
}
