package org.oxaly.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.oxaly.Interfaces.SubCommand;
import org.oxaly.Managers.ArenaManager;
import org.oxaly.Managers.ConfigManager;
import org.oxaly.Objects.Arena;
import org.oxaly.Objects.Spawn;
import org.oxaly.OxalyKnockback;


public class ArenaCommand implements SubCommand {

    private OxalyKnockback plugin;
    private ArenaManager arenamanager;
    private ConfigManager arenasConfig;

    public ArenaCommand(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.arenasConfig = plugin.getArenasConfig();
        this.arenamanager = plugin.getArenaManager();
    }

    @Override
    public String getName() {
        return "arena";
    }

    @Override
    public String getDescription() {
        return "Crea una nueva arena.";
    }

    @Override
    public String getPermission() {
        return "kb.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return false;
        if (args.length == 0) {
            player.sendMessage("§cUso: /kbadmin arena <create|setpos1|setpos2|setspawn|save>");
            return true;
        }
        String action = args[0].toLowerCase();

        switch (action) {
            case "create" -> {
                if (args.length != 2) {
                    player.sendMessage("§cUso: /kbadmin arena create <nombre>");
                    return true;
                }

                if (arenamanager.getArenaEnConstruccion(player) != null) {
                    player.sendMessage("§cYa estás creando una arena. Usa /kbadmin arena save o cancela.");
                    return true;
                }

                Arena arena = new Arena();
                arena.setName(args[1]);
                arenamanager.setArenaEnConstruccion(player, arena);
                player.sendMessage("§aArena §e" + args[1] + "§a en construcción.");
            }

            case "setspawn" -> {
                Arena arena = arenamanager.getArenaEnConstruccion(player);
                if (arena == null) {
                    player.sendMessage("§cNo hay ninguna arena en construcción.");
                    return true;
                }

                arena.setLocation(player.getLocation());
                player.sendMessage("§aSpawn de la arena establecido.");
            }

            case "setpos1" -> {
                Arena arena = arenamanager.getArenaEnConstruccion(player);
                if (arena == null) {
                    player.sendMessage("§cNo hay ninguna arena en construcción.");
                    return true;
                }

                if (arena.getSpawn() == null) arena.setSpawn(new Spawn());
                arena.getSpawn().setPos1(player.getLocation());
                player.sendMessage("§aPosición 1 del área de spawn establecida.");
            }

            case "setpos2" -> {
                Arena arena = arenamanager.getArenaEnConstruccion(player);
                if (arena == null) {
                    player.sendMessage("§cNo hay ninguna arena en construcción.");
                    return true;
                }

                if (arena.getSpawn() == null) arena.setSpawn(new Spawn());
                arena.getSpawn().setPos2(player.getLocation());
                player.sendMessage("§aPosición 2 del área de spawn establecida.");
            }

            case "save" -> {
                Arena arena = arenamanager.getArenaEnConstruccion(player);
                if (arena == null) {
                    player.sendMessage("§cNo hay ninguna arena en construcción.");
                    return true;
                }

                if (arena.getLocation() == null || arena.getSpawn() == null
                        || arena.getSpawn().getPos1() == null || arena.getSpawn().getPos2() == null) {
                    player.sendMessage("§cDebes definir el spawn con /setspawn, /setpos1 y /setpos2 antes de guardar.");
                    return true;
                }

                String path = "arenas." + arena.getName();
                Location loc = arena.getLocation();

                arenasConfig.getConfig().set(path + ".x", loc.getX());
                arenasConfig.getConfig().set(path + ".y", loc.getY());
                arenasConfig.getConfig().set(path + ".z", loc.getZ());
                arenasConfig.getConfig().set(path + ".yaw", loc.getYaw());
                arenasConfig.getConfig().set(path + ".pitch", loc.getPitch());

                saveLocation(path + ".spawn.pos1", arena.getSpawn().getPos1());
                saveLocation(path + ".spawn.pos2", arena.getSpawn().getPos2());

                arenasConfig.save();
                arenamanager.clearArenaEnConstruccion(player);
                player.sendMessage("§aArena guardada correctamente.");
            }

            default -> player.sendMessage("§cSubcomando inválido.");
        }

        return true;
    }

    private void saveLocation(String path, Location loc) {
        arenasConfig.getConfig().set(path + ".x", loc.getX());
        arenasConfig.getConfig().set(path + ".y", loc.getY());
        arenasConfig.getConfig().set(path + ".z", loc.getZ());
    }
}
