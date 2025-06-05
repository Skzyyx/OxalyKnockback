package org.oxaly.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.oxaly.Interfaces.CustomMenu;

public class InventoryListener implements Listener {

    public void openMenu(Player player, CustomMenu menu) {
        player.openInventory(menu.getInventory());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getInventory();

        if (clickedInventory.getHolder(false) instanceof CustomMenu customMenu) {
            customMenu.handleClick(event);
        }
    }
}
