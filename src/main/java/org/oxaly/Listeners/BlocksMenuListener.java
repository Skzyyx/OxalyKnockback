package org.oxaly.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.oxaly.Menus.BlocksMenu;
import org.oxaly.OxalyKnockback;

public class BlocksMenuListener implements Listener {

    private OxalyKnockback plugin;

    public BlocksMenuListener(OxalyKnockback plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMoveItemInventory(InventoryMoveItemEvent event) {

        if (!(event.getDestination().getHolder() instanceof BlocksMenu)) return;
        if (!(event.getInitiator().getHolder() instanceof BlocksMenu)) return;

        event.setCancelled(true);
    }
}
