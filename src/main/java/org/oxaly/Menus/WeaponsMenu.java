package org.oxaly.Menus;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.oxaly.Cosmetics.Weapons;
import org.oxaly.Interfaces.CustomMenu;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.Objects.PlayerData;
import org.oxaly.OxalyKnockback;
import org.oxaly.Utils.ColorUtil;

import java.util.List;

import static org.oxaly.Utils.ColorUtil.*;

public class WeaponsMenu extends PagedMenu<Weapons> implements CustomMenu {

    private OxalyKnockback plugin;
    private PlayerDataManager playerDataManager;

    private Player player;
    private final PlayerData data;

    public WeaponsMenu(Player player) {
        super(List.of(Weapons.values())); // Esto maneja la lógica de páginas
        this.player = player;
        this.plugin = OxalyKnockback.getInstance();
        this.playerDataManager = plugin.getPlayerDataManager();
        this.data = playerDataManager.getPlayerData(player.getUniqueId());
        createInventory(); // esto lo heredas de PagedMenu
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof WeaponsMenu)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

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

            Weapons nuevoArma = Weapons.fromMaterial(clicked.getType());
            if (nuevoArma == null) return;

            List<Weapons> ownedWeapons = data.getCosmeticsData().getWeaponsData().getOwnedWeapons();

            String displayName = LegacyComponentSerializer.builder()
                    .hexColors() // importante para colores RGB
                    .character('§') // o '&' si prefieres, pero § es el símbolo usado por Minecraft internamente
                    .build()
                    .serialize(clicked.displayName())
                    .replace("[", "")
                    .replace("]", "");

            // Si ya tiene el arma
            if (ownedWeapons.contains(nuevoArma)) {

                data.getCosmeticsData().getWeaponsData().setCurrentWeapon(nuevoArma);
                playerDataManager.save(data);
                player.clearActiveItem();
                player.closeInventory();
                player.sendMessage(ColorUtil.format("&dArma actualizada: &b" + displayName));

                // Si no tiene el bloque y puede comprarlo
            } else if (data.getBalance() >= nuevoArma.getCost()) {

                player.clearActiveItem();
                data.setBalance(data.getBalance() - nuevoArma.getCost());
                data.getCosmeticsData().getWeaponsData().getOwnedWeapons().add(nuevoArma);
                data.getCosmeticsData().getWeaponsData().setCurrentWeapon(nuevoArma);
                playerDataManager.save(data);
                player.sendMessage(ColorUtil.format("&dArma comprado y equipado: &b" + displayName));
                player.closeInventory();

                // Si no tiene el arma y no puede comprarlo
            } else {

                player.clearActiveItem();
                player.sendMessage(ColorUtil.format("&cNo tienes dinero suficiente para comprar este arma."));
                player.closeInventory();

            }

        }
    }

    @Override
    protected ItemStack toItemStack(Weapons item) {
        return item.getDisplayItem(data);
    }

    @Override
    protected Component getMenuTitle() {
        return format("Armas");
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
