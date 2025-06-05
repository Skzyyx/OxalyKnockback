package org.oxaly.Menus;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.oxaly.Cosmetics.Blocks;
import org.oxaly.Interfaces.CustomMenu;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.Objects.PlayerData;
import org.oxaly.OxalyKnockback;
import org.oxaly.Utils.ColorUtil;

import java.util.List;

public class BlocksMenu extends PagedMenu<Blocks> implements CustomMenu {

    private OxalyKnockback plugin;
    private PlayerDataManager playerDataManager;

    private Player player;
    private final PlayerData data;

    public BlocksMenu(Player player) {
        super(List.of(Blocks.values())); // Esto maneja la lógica de páginas
        this.player = player;
        this.plugin = OxalyKnockback.getInstance();
        this.playerDataManager = plugin.getPlayerDataManager();
        this.data = playerDataManager.getPlayerData(player.getUniqueId());
        createInventory(); // esto lo heredas de PagedMenu
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof BlocksMenu)) return;
        if (!(event.getWhoClicked() instanceof Player player)) return;

        event.setCancelled(true);

        int slot = event.getRawSlot();

        if (slot == 39) {
            previousPage();
            player.openInventory(inventory);
        } else if (slot == 41) {
            nextPage();
            player.openInventory(inventory);
        } else {
            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || !clicked.hasItemMeta()) return;

            Blocks nuevoBloque = Blocks.fromBlock(clicked.getType());
            if (nuevoBloque == null) return;

            List<Blocks> ownedBlocks = data.getCosmeticsData().getBlocksData().getOwnedBlocks();

            String displayName = LegacyComponentSerializer.builder()
                    .hexColors() // importante para colores RGB
                    .character('§') // o '&' si prefieres, pero § es el símbolo usado por Minecraft internamente
                    .build()
                    .serialize(clicked.displayName())
                    .replace("[", "")
                    .replace("]", "");

            // Si ya tiene el bloque
            if (ownedBlocks.contains(nuevoBloque)) {

                data.getCosmeticsData().getBlocksData().setCurrentBlock(nuevoBloque);
                playerDataManager.save(data);
                player.clearActiveItem();
                player.closeInventory();
                player.sendMessage(ColorUtil.format("&dBloque actualizado: &b" + displayName));

                // Si no tiene el bloque y puede comprarlo
            } else if (data.getBalance() >= nuevoBloque.getCost()) {

                player.clearActiveItem();
                data.setBalance(data.getBalance() - nuevoBloque.getCost());
                data.getCosmeticsData().getBlocksData().getOwnedBlocks().add(nuevoBloque);
                data.getCosmeticsData().getBlocksData().setCurrentBlock(nuevoBloque);
                playerDataManager.save(data);
                player.sendMessage(ColorUtil.format("&dBloque comprado y equipado: &b" + displayName));
                player.closeInventory();

                // Si no tiene el bloque y no puede comprarlo
            } else {

                player.clearActiveItem();
                player.sendMessage(ColorUtil.format("&cNo tienes dinero suficiente para comprar este bloque."));
                player.closeInventory();

            }

        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @Override
    protected ItemStack toItemStack(Blocks item) {
        return item.getDisplayItem(data);
    }

    @Override
    protected Component getMenuTitle() {
        return Component.text("Bloques");
    }
}
