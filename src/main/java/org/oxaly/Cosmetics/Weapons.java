package org.oxaly.Cosmetics;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.oxaly.Objects.PlayerData;

import java.util.List;

import static org.oxaly.Utils.ColorUtil.formatList;

public enum Weapons {

    DEFAULT_WEAPON("default_weapon", Material.STICK, 0, "kb.cosmetics.weapons.default_weapon") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(getMaterial());
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Default"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    SLIME_BALL_WEAPON("slime_ball_weapon", Material.SLIME_BALL, 400, "kb.cosmetics.weapons.slime_ball_weapon") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(getMaterial());
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Slime Ball"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    };

    private final String id;
    private final Material material;
    private final int cost;
    private final String permission;

    Weapons(String id, Material material, int cost, String permission) {
        this.id = id;
        this.material = material;
        this.cost = cost;
        this.permission = permission;
    }

    public abstract ItemStack getDisplayItem(PlayerData data);

    public String getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public int getCost() {
        return cost;
    }

    public String getPermission() {
        return permission;
    }

    public static Weapons fromMaterial(Material material) {
        for (Weapons weapon : values()) {
            if (weapon.getMaterial().equals(material)) return weapon;
        }
        return null;
    }

    public List<Component> getLore(PlayerData data) {

        double monedas = data.getBalance();

        if (data.getCosmeticsData().getBlocksData().getOwnedBlocks().contains(this)) {
            return formatList(
                    "",
                    " &dYa tienes este cosmético.",
                    " &e▸ ¡Clic para equipar!");
        } else if (monedas >= getCost()) {
            return formatList(
                    "",
                    " &dPrecio: $" + getCost(),
                    "",
                    " &e▸ ¡Clic para comprar!");
        } else {
            return formatList(
                    "",
                    " &dPrecio: $" + getCost(),
                    "",
                    " &cNo tienes dinero suficiente para comprar esto.");
        }
    }
}
