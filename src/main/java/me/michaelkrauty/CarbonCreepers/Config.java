package me.michaelkrauty.CarbonCreepers;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created on 6/25/2014.
 *
 * @author michaelkrauty
 */
public class Config {

	private Main main;

	private File configFile = new File(Main.main.getDataFolder() + "/config.yml");
	private YamlConfiguration config = new YamlConfiguration();

	public Config(Main instance) {
		main = instance;
		checkFile();
		reload();
	}

	private void checkFile() {
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				InputStream input = main.getResource("config.yml");
				OutputStream output = new FileOutputStream(configFile);
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = input.read(buffer)) > 0) {
					output.write(buffer, 0, bytesRead);
				}
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void reload() {
		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getInt(String path) {
		return config.getInt(path);
	}

	public boolean getBoolean(String path) {
		return config.getBoolean(path);
	}
}
