package org.oxaly.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.oxaly.Cosmetics.Blocks;
import org.oxaly.Managers.ArenaManager;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.Managers.PlayerManager;
import org.oxaly.Objects.Arena;
import org.oxaly.Objects.PlayerData;
import org.oxaly.OxalyKnockback;

import java.util.ArrayList;
import java.util.List;

public class KitListener implements Listener {

    private OxalyKnockback plugin;

    private final ArenaManager arenaManager;
    private final PlayerManager playerManager;
    private final PlayerDataManager playerDataManager;

    public KitListener(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.arenaManager = plugin.getArenaManager();
        this.playerManager = plugin.getPlayerManager();
        this.playerDataManager = plugin.getPlayerDataManager();
    }

    @EventHandler
    public void onFeatherUse(PlayerInteractEvent event) {

        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;


        if (event.getItem() == null || event.getItem().getType() != Material.FEATHER) return;

        Player player = event.getPlayer();

        ItemStack item = event.getItem().clone();
        int slot = player.getInventory().getHeldItemSlot();

        event.getItem().setAmount(0);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 1, false, false));

        new BukkitRunnable() {
            @Override
            public void run() {
                if (arenaManager.getCurrentArena().getSpawn().isInside(player.getLocation())) return;
                player.getInventory().setItem(slot, item);
            }
        }.runTaskLater(plugin, 15 * 20L);


    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (playerManager.isAdmin(player)) return;

        Location loc = event.getBlock().getLocation();
        ItemStack item = event.getItemInHand().clone();
        item.setAmount(1);

        PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());
        Blocks selectedBlock = data.getCosmeticsData().getBlocksData().getCurrentBlock();

        // Incluir el bloque colocado como la primera fase
        List<Material> phases = new ArrayList<>();
        phases.add(event.getBlock().getType()); // Primera fase: el bloque original colocado
        phases.addAll(selectedBlock.getPhases());
        phases.add(Material.AIR);

        int phasesCount = phases.size() - 1;
        double changeInterval = (double) 10 / phasesCount ;

        Arena current = arenaManager.getCurrentArena();

        Location placedLoc = loc.clone();
        boolean isInSpawnOnPlace = current.getSpawn().isInside(placedLoc);

        for (int i = 1; i <= phasesCount; i++) {
            Material material = phases.get(i);
            new BukkitRunnable() {
                @Override
                public void run() {
                    Arena current = arenaManager.getCurrentArena();
                    if (current == null) return;

                    // ⚠️ Ya no uses player.getLocation(), usa la verificación previa
                    if (isInSpawnOnPlace) return;

                    // Cambia el bloque
                    placedLoc.getBlock().setType(material);

                    // Si es la última fase (AIR) y el jugador no está dentro del spawn
                    if (material == Material.AIR && !arenaManager.getCurrentArena().getSpawn().isInside(player.getLocation())) {

                        Material type = item.getType();

                        // Busca en los slots de inventarios y lo entrega si lo encuentra
                        for (ItemStack invItem : player.getInventory().getContents()) {
                            if (invItem != null && invItem.getType() == type) {
                                if (invItem.getAmount() == invItem.getMaxStackSize()) return;
                                if (invItem.getAmount() < invItem.getMaxStackSize()) {
                                    invItem.setAmount(invItem.getAmount() + 1);
                                    return;
                                }
                            }
                        }

                        if (!arenaManager.getCurrentArena().getSpawn().isInside(player.getLocation())) {
                            player.getInventory().setItem(data.getInventorySlots().getBlocksSlot(),
                                    new ItemStack(data.getCosmeticsData().getBlocksData().getCurrentBlock().getBlock(), 1));
                        }
                    }
                }
            }.runTaskLater(plugin, (long) (changeInterval * i * 20));
        }
    }
}
