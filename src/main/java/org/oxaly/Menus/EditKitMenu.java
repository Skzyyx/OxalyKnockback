package org.oxaly.Menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.oxaly.Interfaces.CustomMenu;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.Objects.Cosmetics.InventorySlots;
import org.oxaly.Objects.PlayerData;
import org.oxaly.OxalyKnockback;

public class EditKitMenu implements CustomMenu, Listener {

    private Inventory inventory;

    private final OxalyKnockback plugin;
    private final PlayerDataManager playerDataManager;

    private Player player;

    public EditKitMenu(Player player) {
        this.plugin = OxalyKnockback.getInstance();
        this.player = player;
        this.playerDataManager = plugin.getPlayerDataManager();
        this.inventory = Bukkit.createInventory(this, 9, Component.text("Edit Kit"));

        PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());

        InventorySlots slots = data.getInventorySlots();

        ItemStack stick = new ItemStack(Material.STICK);
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        ItemStack blocks = new ItemStack(Material.SANDSTONE);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack feather = new ItemStack(Material.FEATHER);
        ItemStack arrow = new ItemStack(Material.ARROW);

        inventory.setItem(slots.getStickSlot(), stick);
        inventory.setItem(slots.getEnderPearlSlot(), pearl);
        inventory.setItem(slots.getBlocksSlot(), blocks);
        inventory.setItem(slots.getBowSlot(), bow);
        inventory.setItem(slots.getFeatherSlot(), feather);
        inventory.setItem(slots.getArrowSlot(), arrow);

    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        // Verifica si el inventario superior es tu menú personalizado
        if (!(event.getView().getTopInventory().getHolder() instanceof EditKitMenu)) {
            return;
        }

        // Impide cualquier acción que mueva ítems entre inventarios
        if (event.getClickedInventory() == null || event.getClickedInventory() != event.getView().getTopInventory()) {
            event.setCancelled(true);
            return;
        }

        // También cancelar si se usó una tecla de número (1-9) para mover ítems
        if (event.getClick() != ClickType.LEFT && event.getClick() != ClickType.RIGHT) {
            event.setCancelled(true);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
