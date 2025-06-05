package org.oxaly.Menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.oxaly.Interfaces.CustomMenu;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.OxalyKnockback;

public class UpgradeMenu implements CustomMenu {

    private Inventory inventory;

    private final OxalyKnockback plugin;
    private final PlayerDataManager playerDataManager;

    private Player player;

    public UpgradeMenu(Player player) {
        this.plugin = OxalyKnockback.getInstance();
        this.player = player;
        this.playerDataManager = plugin.getPlayerDataManager();
        this.inventory = Bukkit.createInventory(this, 9, Component.text("Upgrade Weapon"));


    }

    @Override
    public void handleClick(InventoryClickEvent event) {

    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
