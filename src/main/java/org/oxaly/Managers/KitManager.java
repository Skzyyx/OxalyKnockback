package org.oxaly.Managers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.oxaly.Objects.Cosmetics.InventorySlots;
import org.oxaly.Objects.PlayerData;
import org.oxaly.OxalyKnockback;

import java.util.UUID;

public class KitManager {

    private OxalyKnockback plugin;

    private PlayerDataManager playerDataManager;

    private Inventory spawnInventory;

    public KitManager() {
        this.plugin = OxalyKnockback.getInstance();
        this.playerDataManager = plugin.getPlayerDataManager();
        buildSpawnInventory();
    }

    public void setKitInventory(Player player) {
        PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());

        Inventory inv = buildKitInventory(data);

        player.getInventory().clear();
        player.getInventory().setContents(inv.getContents());
    }

    public void setSpawnInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setContents(spawnInventory.getContents());
    }

    public void addPearl(Player player) {
        PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());

        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.displayName(Component.text("§b§lQuantum Orb"));
        pearl.setItemMeta(pearlMeta);
        player.getInventory().setItem(data.getInventorySlots().getEnderPearlSlot(), pearl);
    }

    private Inventory buildKitInventory(PlayerData data) {
        Inventory kitInv = Bukkit.createInventory(null, InventoryType.PLAYER, Component.text("Kit"));

        ItemStack stick = new ItemStack(data.getCosmeticsData().getWeaponsData().getCurrentWeapon().getMaterial());
        ItemMeta stickMeta = stick.getItemMeta();
        stickMeta.displayName(Component.text("§5§lPulseRod"));
        //stickMeta.addEnchant(Enchantment.SHARPNESS, 1, true);
        stickMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        stickMeta.setUnbreakable(true);
        stickMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stick.setItemMeta(stickMeta);

        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.displayName(Component.text("§b§lQuantum Orb"));
        pearl.setItemMeta(pearlMeta);

        ItemStack blocks = new ItemStack(data.getCosmeticsData().getBlocksData().getCurrentBlock().getBlock());
        ItemMeta blocksMeta = blocks.getItemMeta();
        blocksMeta.displayName(Component.text("§d§lBioBrick"));
        blocks.setItemMeta(blocksMeta);
        blocks.setAmount(64);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setUnbreakable(true);
        bowMeta.displayName(Component.text("§e§lChronoBow"));
        bowMeta.addEnchant(Enchantment.SHARPNESS, 1, true);
        bow.setItemMeta(bowMeta);

        ItemStack feather = new ItemStack(Material.FEATHER);
        ItemMeta featherMeta = feather.getItemMeta();
        featherMeta.displayName(Component.text("§6§lGalewing"));
        feather.setItemMeta(featherMeta);

        InventorySlots slots = data.getInventorySlots();

        kitInv.setItem(slots.getStickSlot(), stick);
        kitInv.setItem(slots.getEnderPearlSlot(), pearl);
        kitInv.setItem(slots.getBlocksSlot(), blocks);
        kitInv.setItem(slots.getBowSlot(), bow);
        kitInv.setItem(slots.getFeatherSlot(), feather);
        kitInv.setItem(slots.getArrowSlot(), new ItemStack(Material.ARROW));

        return kitInv;
    }

    private void buildSpawnInventory() {
        spawnInventory = Bukkit.createInventory(null, InventoryType.PLAYER, Component.text("Spawn"));

        ItemStack shop = new ItemStack(Material.EMERALD);
        ItemMeta stickMeta = shop.getItemMeta();
        stickMeta.displayName(Component.text("§5§lMercado"));
        shop.setItemMeta(stickMeta);

        spawnInventory.setItem(4, shop);
    }

    public Inventory getSpawnInventory() {
        return spawnInventory;
    }
}
