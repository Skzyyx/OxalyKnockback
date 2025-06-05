package org.oxaly.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.oxaly.Managers.ArenaManager;
import org.oxaly.Managers.KitManager;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.Managers.PlayerManager;
import org.oxaly.Menus.UpgradeMenu;
import org.oxaly.Objects.Arena;
import org.oxaly.Objects.PlayerData;
import org.oxaly.Objects.Spawn;
import org.oxaly.OxalyKnockback;

public class FFAListener implements Listener {

    private final OxalyKnockback plugin;
    private final KitManager kitManager;
    private final PlayerManager playerManager;
    private final ArenaManager arenaManager;
    private final PlayerDataManager playerDataManager;

    public FFAListener(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.kitManager = plugin.getKitManager();
        this.playerManager = plugin.getPlayerManager();
        this.arenaManager = plugin.getArenaManager();
        this.playerDataManager = plugin.getPlayerDataManager();
    }

    /**
     * Para teletransportar al jugador al spawn.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Solo teletransporta si es la primera vez que entra
        if (!player.hasPlayedBefore()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    arenaManager.tpSpawn(player);
                    playerDataManager.getPlayerData(player.getUniqueId());
                }
            }.runTaskLater(plugin, 5L); // 5 ticks (1/4 segundo) después
        }

        player.setRespawnLocation(arenaManager.getCurrentArena().getLocation());
        arenaManager.tpSpawn(player);
        kitManager.setSpawnInventory(player);
    }

    /**
     * Para cancelar el daño por ender pearl.
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        if (event.getDamager() instanceof EnderPearl) {
            event.setCancelled(true);
        }
    }

    /**
     * Para manejar el uso de la ender pearl.
     */
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof EnderPearl)) return;

        ProjectileSource shooter = event.getEntity().getShooter();
        if (!(shooter instanceof Player player)) return;

        player.getServer().getScheduler().runTaskLater(OxalyKnockback.getInstance(), () -> {
            player.setCooldown(Material.ENDER_PEARL, 20 * 10);
            kitManager.addPearl(player);
        }, 1L);
    }

    /**
     * Para manejar cooldowns y eliminar drops al morir.
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getPlayer().setCooldown(Material.ENDER_PEARL, 0);
        event.getDrops().clear();
    }

    /**
     * Para teletransportar al jugador al spawn cuando respawenea.
     */
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        event.setRespawnLocation(arenaManager.getCurrentArena().getLocation());
        player.getServer().getScheduler().runTaskLater(plugin, () -> {
            player.setFoodLevel(20);
            player.setSaturation(10);
            arenaManager.tpSpawn(player);
            kitManager.setSpawnInventory(player);
        }, 1L);
    }

    /**
     * Para cancelar el movimiento de items en el inventario del jugador.
     * Si está en modo administrador, el evento no se cancela.
     */
    @EventHandler
    public void onInventoryMoveItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (playerManager.isAdmin(player)) return;

        if (event.getClickedInventory() == player.getInventory()) event.setCancelled(true);
    }

    /**
     * Para cancelar el movimiento de items en el inventario del jugador.
     * Si está en modo administrador, el evento no se cancela.
     */
    @EventHandler
    public void onInventoryMoveItem(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (playerManager.isAdmin(player)) return;
        event.setCancelled(true);
    }

    /**
     * Para cancelar el movimiento de items en el inventario del jugador.
     * Si está en modo administrador, el evento no se cancela.
     */
    @EventHandler
    public void onInventoryMoveItem(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (playerManager.isAdmin(player)) return;
        event.setCancelled(true);
    }

    /**
     * Para manejar el evento de disparar una flecha con el arco.
     */
    @EventHandler
    public void onUseBow(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        new BukkitRunnable() {

            @Override
            public void run() {
                for (ItemStack invItem : player.getInventory().getContents()) {
                    if (invItem != null && invItem.getType() == Material.ARROW) {
                        if (invItem.getAmount() < 1) {
                            invItem.setAmount(invItem.getAmount() + 1);
                            break;
                        }
                    }

                    PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());

                    if (arenaManager.getCurrentArena().getSpawn().isInside(player.getLocation())) return;
                    player.getInventory().setItem(data.getInventorySlots().getArrowSlot(), new ItemStack(Material.ARROW));
                }
            }

        }.runTaskLater(OxalyKnockback.getInstance(), 5 * 20L);
    }

    /**
     * Para manejar cuando un jugador rompe un bloque.
     * Si el jugador está en modo administrador, el evento no se cancela.
     */
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (playerManager.isAdmin(player)) return;
        event.setCancelled(true);
    }

    /**
     * Para manejar a qué golpeó una flecha al ser disparada
     */
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow arrow)) return;

        // Si golpeó a un jugador, no hacemos nada
        if (event.getHitEntity() instanceof Player) return;

        // Si no golpeó a un jugador (puede ser bloque o nada), se remueve
        arrow.remove();
    }

    /**
     * Para manejar la entrega de inventarios al salir/entrar al spawn.
     */
    @EventHandler
    public void onLeaveSpawn(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Arena current = arenaManager.getCurrentArena();
        if (current == null) return; // <- 🔒 PREVENIR ERROR SI NO HAY ARENA

        Spawn spawn = current.getSpawn();

        if (spawn.isInside(event.getFrom()) && !spawn.isInside(event.getTo())) {
            kitManager.setKitInventory(player);
            player.closeInventory();
            return;
        }

        if (!spawn.isInside(event.getFrom()) && spawn.isInside(event.getTo())) {
            player.getInventory().clear();
            kitManager.setSpawnInventory(player);
        }
    }

    /**
     * Para manejar la entrega de inventarios al salir/entrar al spawn.
     */
    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        Arena current = arenaManager.getCurrentArena();
        if (current == null) return; // <- 🔒 PREVENIR ERROR

        Spawn spawn = current.getSpawn();

        boolean fromInside = spawn.isInside(from);
        boolean toInside = spawn.isInside(to);

        if (!fromInside && toInside) {
            player.getInventory().clear();
            kitManager.setSpawnInventory(player);
        }

        if (fromInside && !toInside) {
            kitManager.setKitInventory(player);
        }
    }

    /**
     * Para cancelar el daño de golpe si algun participante está dentro del spawn.
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player victim && event.getDamager() instanceof Player damager)) return;

        Arena arena = arenaManager.getCurrentArena();
        if (arena == null || arena.getSpawn() == null) return;

        Spawn spawn = arena.getSpawn();

        // Cancela el daño si el atacante o la víctima están dentro del spawn
        if (spawn.isInside(damager.getLocation()) || spawn.isInside(victim.getLocation())) {
            event.setCancelled(true);
        }
    }

    /**
     * Para matar al jugador si cae mas allá de la capa 0.
     */
    @EventHandler
    public void onFall(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        player.setFoodLevel(20);

        if (player.getGameMode() != GameMode.SURVIVAL) return;
        if (playerManager.isAdmin(player)) return;

        if (event.getTo().getY() < 0) player.setHealth(0.0);
    }

    /**
     * Para cancelar daños según su causa.
     */
    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) event.setCancelled(true);

        if (event.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION)) event.setCancelled(true);

    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {

        if (playerManager.isAdmin(event.getPlayer())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onUpgradeClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Verifica que sea click derecho (aire o bloque)
        if (!event.getAction().isRightClick()) return;

        // Verifica que esté haciendo shift
        if (!player.isSneaking()) return;

        // Verifica que tenga el slot del palo
        PlayerData data = playerDataManager.getPlayerData(player.getUniqueId());
        int heldSlot = player.getInventory().getHeldItemSlot();
        int stickSlot = data.getInventorySlots().getStickSlot();

        if (heldSlot != stickSlot) return;

        // Abrir el menú
        player.openInventory(new UpgradeMenu(player).getInventory());
    }
}

