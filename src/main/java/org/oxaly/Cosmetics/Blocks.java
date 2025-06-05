package org.oxaly.Cosmetics;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.oxaly.Objects.PlayerData;

import static org.oxaly.Utils.ColorUtil.*;

import java.util.Arrays;
import java.util.List;

public enum Blocks {

    DEFAULT_BLOCK("default_block", Material.SANDSTONE, List.of(
            Material.YELLOW_TERRACOTTA,
            Material.RED_TERRACOTTA), 0, null) {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.SANDSTONE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("&7&lDefault"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    STONE_BLOCK("stone_block", Material.STONE, List.of(
            Material.COBBLESTONE,
            Material.MOSSY_COBBLESTONE), 300, "kb.cosmetics.blocks.stone_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.STONE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Stone"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    OCEAN_BLOCK("ocean_block", Material.DARK_PRISMARINE, List.of(
            Material.PRISMARINE_BRICKS,
            Material.PRISMARINE), 500, "kb.cosmetics.blocks.ocean_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.DARK_PRISMARINE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Ocean"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    ARCOIRIS_WOOL_BLOCK("arcoiris_wool_block", Material.RED_WOOL, List.of(
            Material.RED_WOOL,
            Material.ORANGE_WOOL,
            Material.YELLOW_WOOL,
            Material.LIME_WOOL,
            Material.BLUE_WOOL,
            Material.CYAN_WOOL,
            Material.PURPLE_WOOL
    ), 800, "kb.cosmetics.blocks.arcoiris_wool_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.RED_WOOL);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Arcoiris Wool"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    VOLCANIC_BLOCK("volcanic_block", Material.BLACKSTONE, List.of(
            Material.BLACKSTONE,
            Material.POLISHED_BLACKSTONE,
            Material.POLISHED_BLACKSTONE_BRICKS
    ), 550, "kb.cosmetics.blocks.volcanic_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.BLACKSTONE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Volcanic"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    AUTUMN_BLOCK("autumn_block", Material.ORANGE_CONCRETE, List.of(
            Material.ORANGE_CONCRETE,
            Material.BROWN_CONCRETE,
            Material.RED_CONCRETE
    ), 500, "kb.cosmetics.blocks.autumn_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.ORANGE_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Autumn"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    FROST_BLOCK("frost_block", Material.LIGHT_BLUE_CONCRETE, List.of(
            Material.LIGHT_BLUE_CONCRETE,
            Material.WHITE_CONCRETE,
            Material.CYAN_CONCRETE
    ), 600, "kb.cosmetics.blocks.frost_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Frost"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    GALAXY_BLOCK("galaxy_block", Material.OBSIDIAN, List.of(
            Material.OBSIDIAN,
            Material.PURPLE_CONCRETE,
            Material.BLACK_CONCRETE
    ), 900, "kb.cosmetics.blocks.galaxy_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.OBSIDIAN);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Galaxy"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    CLAY_BLOCK("clay_block", Material.TERRACOTTA, List.of(
            Material.TERRACOTTA,
            Material.WHITE_TERRACOTTA,
            Material.LIGHT_GRAY_TERRACOTTA
    ), 350, "kb.cosmetics.blocks.clay_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Clay"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    FOREST_BLOCK("forest_block", Material.GREEN_CONCRETE, List.of(
            Material.GREEN_CONCRETE,
            Material.LIME_CONCRETE,
            Material.BROWN_CONCRETE
    ), 500, "kb.cosmetics.blocks.forest_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Forest"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    URBAN_BLOCK("urban_block", Material.GRAY_CONCRETE, List.of(
            Material.GRAY_CONCRETE,
            Material.LIGHT_GRAY_CONCRETE,
            Material.BLACK_CONCRETE
    ), 600, "kb.cosmetics.blocks.urban_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.GRAY_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Urban"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    SUNSET_BLOCK("sunset_block", Material.MAGENTA_CONCRETE, List.of(
            Material.MAGENTA_CONCRETE,
            Material.PINK_CONCRETE,
            Material.ORANGE_CONCRETE
    ), 550, "kb.cosmetics.blocks.sunset_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.MAGENTA_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Sunset"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    INDUSTRIAL_BLOCK("industrial_block", Material.IRON_BLOCK, List.of(
            Material.IRON_BLOCK,
            Material.STONE,
            Material.GRAY_CONCRETE
    ), 650, "kb.cosmetics.blocks.industrial_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.IRON_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Industrial"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    CRYSTAL_BLOCK("crystal_block", Material.LIGHT_BLUE_STAINED_GLASS, List.of(
            Material.LIGHT_BLUE_STAINED_GLASS,
            Material.PINK_STAINED_GLASS,
            Material.PURPLE_STAINED_GLASS
    ), 700, "kb.cosmetics.blocks.crystal_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Crystal"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    PASTEL_BLOCK("pastel_block", Material.PINK_CONCRETE, List.of(
            Material.PINK_CONCRETE,
            Material.LIGHT_BLUE_CONCRETE,
            Material.LIGHT_GRAY_CONCRETE
    ), 450, "kb.cosmetics.blocks.pastel_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.PINK_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Pastel"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    SAKURA_BLOCK("sakura_block", Material.PINK_TERRACOTTA, List.of(
            Material.PINK_TERRACOTTA,
            Material.PINK_CONCRETE,
            Material.WHITE_CONCRETE
    ), 600, "kb.cosmetics.blocks.sakura_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.PINK_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Sakura"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    BRICKY_BLOCK("bricky_block", Material.BRICKS, List.of(
            Material.BRICKS,
            Material.RED_CONCRETE,
            Material.BROWN_CONCRETE
    ), 300, "kb.cosmetics.blocks.bricky_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.BRICKS);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Bricky"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    NEBULA_BLOCK("nebula_block", Material.PURPLE_CONCRETE, List.of(
            Material.PURPLE_CONCRETE,
            Material.MAGENTA_CONCRETE,
            Material.BLUE_CONCRETE
    ), 850, "kb.cosmetics.blocks.nebula_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.PURPLE_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Nebula"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    },

    BONE_BLOCK("bone_block", Material.BONE_BLOCK, List.of(
            Material.BONE_BLOCK,
            Material.WHITE_CONCRETE,
            Material.QUARTZ_BLOCK
    ), 400, "kb.cosmetics.blocks.bone_block") {
        @Override
        public ItemStack getDisplayItem(PlayerData data) {
            ItemStack item = new ItemStack(Material.BONE_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text("Bone"));
            meta.lore(getLore(data));
            item.setItemMeta(meta);
            return item;
        }
    };


    private final String id;
    private final Material block;
    private final List<Material> phases;
    private final int cost;
    private final String permission;

    Blocks(String id, Material block, List<Material> phases, int cost, String permission) {
        this.id = id;
        this.block = block;
        this.phases = phases;
        this.cost = cost;
        this.permission = permission;
    }

    public abstract ItemStack getDisplayItem(PlayerData data);

    public String getId() {
        return id;
    }

    public Material getBlock() {
        return block;
    }

    public List<Material> getPhases() {
        return phases;
    }

    public int getCost() {
        return cost;
    }

    public String getPermission() {
        return permission;
    }

    // Metodo estático para buscar por ID
    public static Blocks fromId(String id) {
        for (Blocks block : values()) {
            if (block.getId().equalsIgnoreCase(id)) return block;
        }
        return null;
    }

    public static Blocks fromBlock(Material block) {
        for (Blocks blocks : values()) {
            if (blocks.getBlock().equals(block)) return blocks;
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
