package org.oxaly.Managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;
    private final String fileName;
    private final File file;
    private FileConfiguration config;

    public ConfigManager(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        if (!fileName.endsWith(".yml")) {
            fileName += ".yml";
        }

        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);

        createOrLoadFile();
    }

    private void createOrLoadFile() {
        if (!file.exists()) {
            try {
                plugin.getDataFolder().mkdirs();
                if (plugin.getResource(fileName) != null) {
                    plugin.saveResource(fileName, false); // Copia desde resources si existe
                } else {
                    file.createNewFile(); // Crea un archivo vacío
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
