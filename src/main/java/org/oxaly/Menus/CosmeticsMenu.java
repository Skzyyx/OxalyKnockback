package org.oxaly.Menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.oxaly.Interfaces.CustomMenu;
import org.oxaly.Utils.ColorUtil;

import static org.oxaly.Utils.ColorUtil.*;

public class CosmeticsMenu implements CustomMenu {

    Inventory inventory;

    public CosmeticsMenu() {
        this.inventory = Bukkit.createInventory(this, 36, format("&#F53B9C&lC&#F541A0&lo&#F547A4&ls&#F54EA9&lm&#F454AD&le&#F45AB1&lt&#F460B5&li&#F467BA&lc&#F46DBE&ls &#F379C6&lM&#F380CB&le&#F386CF&ln&#F38CD3&lu"));

        ItemStack blocks = new ItemStack(Material.SANDSTONE);
        ItemMeta blocksMeta = blocks.getItemMeta();
        blocksMeta.displayName(format("&b&lBloques"));
        blocks.setItemMeta(blocksMeta);

        inventory.setItem(11, blocks);

        ItemStack weapons = new ItemStack(Material.STICK);
        ItemMeta weaponsMeta = weapons.getItemMeta();
        weaponsMeta.displayName(format("&d&lArmas"));
        weapons.setItemMeta(weaponsMeta);

        inventory.setItem(21, weapons);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {


        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null || !(clickedInventory.getHolder() instanceof CosmeticsMenu)) return;

        if (!(event.getWhoClicked() instanceof Player player)) return;

        event.setCancelled(true);

        int slot = event.getSlot();

        switch (slot) {

            case 11 -> player.openInventory(new BlocksMenu(player).getInventory());

            case 21 -> player.openInventory(new WeaponsMenu(player).getInventory());

            default -> {
                return;
            }
        }

    }
}
