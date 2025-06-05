package org.oxaly.Interfaces;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public interface CustomMenu extends InventoryHolder {

    void handleClick(InventoryClickEvent event);
}
