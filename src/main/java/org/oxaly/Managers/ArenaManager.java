package org.oxaly.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.oxaly.Objects.Arena;
import org.oxaly.Objects.Spawn;
import org.oxaly.OxalyKnockback;

import java.util.*;

public class ArenaManager {

    private OxalyKnockback plugin;
    private final ConfigManager arenasConfig;

    private PlayerManager playerManager;

    private List<Arena> arenas = new ArrayList<>();

    private final Map<UUID, Arena> arenasEnConstruccion = new HashMap<>();

    private Arena currentArena;


    private int currentIndex = 0;

    public ArenaManager() {
        this.plugin = OxalyKnockback.getInstance();
        this.arenasConfig = plugin.getArenasConfig();
        this.playerManager = plugin.getPlayerManager();

        // Retrasar la carga hasta que el mundo esté cargado completamente
        new BukkitRunnable() {
            @Override
            public void run() {
                loadArenas();
                startArenaRotation();
                playerManager.tpAllSpawn(currentArena.getLocation());
            }
        }.runTaskLater(plugin, 20L); // Espera 1 segundo (20 ticks)
    }

    public void loadArenas() {
        if (arenasConfig.getConfig().isConfigurationSection("arenas")) {
            ConfigurationSection section = arenasConfig.getConfig().getConfigurationSection("arenas");

            assert section != null;
            for (String arenaName : section.getKeys(false)) {
                double x = section.getDouble(arenaName + ".x");
                double y = section.getDouble(arenaName + ".y");
                double z = section.getDouble(arenaName + ".z");
                float yaw = (float) section.getDouble(arenaName + ".yaw");
                float pitch = (float) section.getDouble(arenaName + ".pitch");

                double pos1X = section.getDouble(arenaName + ".spawn.pos1.x");
                double pos1Y = section.getDouble(arenaName + ".spawn.pos1.y");
                double pos1Z = section.getDouble(arenaName + ".spawn.pos1.z");

                double pos2X = section.getDouble(arenaName + ".spawn.pos2.x");
                double pos2Y = section.getDouble(arenaName + ".spawn.pos2.y");
                double pos2Z = section.getDouble(arenaName + ".spawn.pos2.z");

                System.out.println("Arena: " + arenaName + " -> x=" + x + ", y=" + y);
                arenas.add(new Arena(
                        arenaName,
                        new Location(Bukkit.getWorld("kb"),  x, y, z, yaw, pitch),
                        new Spawn(
                                new Location(Bukkit.getWorld("kb"), pos1X, pos1Y, pos1Z),
                                new Location(Bukkit.getWorld("kb"), pos2X, pos2Y, pos2Z)
                        )
                ));
            }

            if (!arenas.isEmpty()) {
                currentArena = arenas.getFirst(); // ← Aquí inicializas una arena por defecto
            }
        }
    }

    private void startArenaRotation() {
        if (arenas.isEmpty()) return;

        long intervalTicks = 5 * 60 *  20L; // 10 minutos en ticks

        new BukkitRunnable() {
            @Override
            public void run() {
                // Inicia cuenta regresiva 5 segundos antes del cambio
                for (int i = 5; i >= 1; i--) {
                    int secondsLeft = i;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            String nextArenaName = arenas.get(currentIndex).getName();
                            Bukkit.broadcastMessage("§e¡La arena cambiará a §6" + nextArenaName + " §een §c" + secondsLeft + " §esegundo" + (secondsLeft == 1 ? "!" : "s!"));
                        }
                    }.runTaskLater(plugin, intervalTicks - (secondsLeft * 20L));
                }

                // Cambio de arena
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        currentArena = arenas.get(currentIndex);

                        // Eliminar ender pearls
                        Bukkit.getWorld("kb").getEntities().forEach(entity -> {
                            if (entity instanceof EnderPearl) entity.remove();
                        });

                        // Teleportar y anunciar
                        playerManager.tpAllSpawn(currentArena.getLocation());
                        Bukkit.broadcastMessage("§a¡La arena ha cambiado a §b" + currentArena.getName() + "§a!");

                        // Avanzar al siguiente índice
                        currentIndex = (currentIndex + 1) % arenas.size();
                    }
                }.runTaskLater(plugin, intervalTicks);
            }
        }.runTaskTimer(plugin, 0L, intervalTicks);
    }

    public Arena getCurrentArena() {
        return currentArena;
    }

    public void reloadArenas() {
        arenas = new ArrayList<>();
        loadArenas();
    }

    public void setArenaEnConstruccion(Player player, Arena arena) {
        arenasEnConstruccion.put(player.getUniqueId(), arena);
    }

    public Arena getArenaEnConstruccion(Player player) {
        return arenasEnConstruccion.get(player.getUniqueId());
    }

    public void clearArenaEnConstruccion(Player player) {
        arenasEnConstruccion.remove(player.getUniqueId());
    }

    public void tpSpawn(Player player) {
        player.teleport(currentArena.getLocation());
    }
}
