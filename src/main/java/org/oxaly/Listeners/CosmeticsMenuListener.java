package org.oxaly.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.oxaly.Interfaces.CustomMenu;
import org.oxaly.Menus.BlocksMenu;
import org.oxaly.Managers.KitManager;
import org.oxaly.Menus.CosmeticsMenu;
import org.oxaly.OxalyKnockback;

public class CosmeticsMenuListener implements Listener {

    private final KitManager kitManager;
    private final InventoryListener inventoryManager;


    public CosmeticsMenuListener(OxalyKnockback plugin) {
        this.kitManager = plugin.getKitManager();
        this.inventoryManager = plugin.getInventoryListener();
    }

    @EventHandler
    public void onOpenShop(PlayerInteractEvent event) {

        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.EMERALD) return;

        Player player = event.getPlayer();

        inventoryManager.openMenu(player, new CosmeticsMenu());
    }
}
