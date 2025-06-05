package org.oxaly.Managers;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.oxaly.Listeners.*;
import org.oxaly.Menus.BlocksMenu;
import org.oxaly.Menus.CosmeticsMenu;
import org.oxaly.OxalyKnockback;

public class ListenerManager {

    private final OxalyKnockback plugin;

    public ListenerManager(OxalyKnockback plugin) {
        this.plugin = plugin;
    }

    public void registerListeners() {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new FFAListener(plugin), plugin);
        pm.registerEvents(new CosmeticsMenuListener(plugin), plugin);
        pm.registerEvents(new BlocksMenuListener(plugin), plugin);
        pm.registerEvents(new EditKitMenuListener(plugin), plugin);
        pm.registerEvents(new InventoryListener(), plugin);
        pm.registerEvents(new KitListener(plugin), plugin);
        pm.registerEvents(new PlayerDataListener(plugin), plugin);
    }
}
