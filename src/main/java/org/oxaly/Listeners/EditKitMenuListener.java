package org.oxaly.Listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.Menus.EditKitMenu;
import org.oxaly.Objects.Cosmetics.InventorySlots;
import org.oxaly.Objects.PlayerData;
import org.oxaly.OxalyKnockback;

public class EditKitMenuListener implements Listener {

    private final OxalyKnockback plugin;
    private final PlayerDataManager playerDataManager;

    public EditKitMenuListener(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.playerDataManager = plugin.getPlayerDataManager();
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof EditKitMenu)) {
            return;
        }

        // Cancela si alguno de los slots arrastrados no pertenece al menú
        for (int slot : event.getRawSlots()) {
            if (slot >= event.getView().getTopInventory().getSize()) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof EditKitMenu)) return;

        Inventory inv = event.getInventory();

        int stickSlot = 0;
        int pearlSlot = 1;
        int blocksSlot = 2;
        int bowSlot = 3;
        int featherSlot = 4;
        int arrowSlot = 8;

        for (int slot = 0; slot < inv.getSize(); slot++) {
            ItemStack item = inv.getItem(slot);
            if (item == null || item.getType().isAir()) continue;

            switch (item.getType()) {
                case STICK:
                    stickSlot = slot;
                    break;
                case ENDER_PEARL:
                    pearlSlot = slot;
                    break;
                case SANDSTONE:
                    blocksSlot = slot;
                    break;
                case BOW:
                    bowSlot = slot;
                    break;
                case FEATHER:
                    featherSlot = slot;
                    break;
                case ARROW:
                    arrowSlot = slot;
                    break;
                default:
                    break;
            }
        }

        // Aquí puedes guardar los valores en InventorySlots u otra lógica
        InventorySlots slots = new InventorySlots(
                stickSlot, pearlSlot, blocksSlot, bowSlot, featherSlot, arrowSlot
        );

        Player player = (Player) event.getPlayer();

        // Guardar slots en tu estructura PlayerData, base de datos, etc.
        PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());
        data.setInventorySlots(slots);
        playerDataManager.save(data);
        player.sendMessage(Component.text("Posición de inventario actualizada."));
    }
}
