package org.oxaly;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.oxaly.Connections.RedisConnection;
import org.oxaly.Expansions.PlaceholderAPIExpansion;
import org.oxaly.Listeners.InventoryListener;
import org.oxaly.Managers.*;
import org.oxaly.Services.MongoPlayerDataService;
import org.oxaly.Services.RedisPlayerDataCache;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class OxalyKnockback extends JavaPlugin {

    private static OxalyKnockback plugin;

    private ConfigManager langConfig;
    private ConfigManager settingsConfig;
    private ConfigManager arenasConfig;

    private RedisPlayerDataCache redisCache;
    private MongoPlayerDataService monngoService;

    private ArenaManager arenaManager;
    private PlayerDataManager playerDataManager;
    private KitManager kitManager;
    private InventoryListener inventoryListener;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        plugin = this;

        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        langConfig = new ConfigManager(this, "lang");
        settingsConfig = new ConfigManager(this, "settings");
        arenasConfig = new ConfigManager(this, "arenas");

        redisCache = new RedisPlayerDataCache();
        monngoService = new MongoPlayerDataService();

        playerManager = new PlayerManager();
        arenaManager = new ArenaManager();
        playerDataManager = new PlayerDataManager(redisCache, monngoService);
        kitManager = new KitManager();
        inventoryListener = new InventoryListener();

        new ListenerManager(this).registerListeners();
        new CommandManager(this).register();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
            new PlaceholderAPIExpansion(this).register(); //
        }

        // Esperamos un poco para asegurarnos de que los mundos estén cargados
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for (World world : Bukkit.getWorlds()) {
                world.setAutoSave(false);
                getLogger().info("AutoSave desactivado para el mundo: " + world.getName());
            }
        }, 20L); // 1 segundo después de que el servidor se encienda

    }

    @Override
    public void onDisable() {
        if (playerDataManager != null) {
            playerDataManager.flushRedisToMongo();
        }
        RedisConnection.close();

        for (World world : Bukkit.getWorlds()) {
            world.setAutoSave(false);
        }
    }

    public static OxalyKnockback getInstance() {
        return plugin;
    }

    public ConfigManager getLangConfig() {
        return langConfig;
    }

    public ConfigManager getSettingsConfig() {
        return settingsConfig;
    }

    public ConfigManager getArenasConfig() {
        return arenasConfig;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public InventoryListener getInventoryListener() {
        return inventoryListener;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
}
