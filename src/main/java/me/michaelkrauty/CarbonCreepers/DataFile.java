package me.michaelkrauty.CarbonCreepers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.MaterialData;

import java.io.File;
import java.util.Set;

/**
 * Created on 6/24/2014.
 *
 * @author michaelkrauty
 */
public class DataFile {

	private File dataFile = new File(Main.main.getDataFolder() + "/data.yml");
	private YamlConfiguration data = new YamlConfiguration();

	public DataFile() {
		checkFile();
		reload();
	}

	private void checkFile() {
		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void reload() {
		try {
			data.load(dataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			data.save(dataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void set(String path, String value) {
		data.set(path, value);
		save();
		reload();
	}

	public void set(String path, int value) {
		data.set(path, value);
		save();
		reload();
	}

	public void set(String path, MaterialData value) {
		data.set(path, value.toItemStack());
		save();
		reload();
	}

	public String getString(String path) {
		return data.getString(path);
	}

	public int getInt(String path) {
		return data.getInt(path);
	}

	public byte getByte(String path) {
		return (byte) data.getInt(path);
	}

	public MaterialData getMaterialData(String path) {
		return data.getItemStack(path).getData();
	}

	public Set<String> getKeys(String path) {
		return data.getConfigurationSection(path).getKeys(true);
	}

	public void delete(String path) {
		data.set(path, null);
		save();
		reload();
	}
}
