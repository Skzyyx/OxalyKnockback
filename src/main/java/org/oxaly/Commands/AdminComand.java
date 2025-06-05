package org.oxaly.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.oxaly.Interfaces.SubCommand;
import org.oxaly.Managers.PlayerManager;
import org.oxaly.OxalyKnockback;

public class AdminComand implements SubCommand {

    private OxalyKnockback plugin;
    private final PlayerManager playerManager;

    public AdminComand(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.playerManager = plugin.getPlayerManager();
    }

    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String getDescription() {
        return "Alterna el estado de administrador.";
    }

    @Override
    public String getPermission() {
        return "kb.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if(!(sender instanceof Player player)) return false;

        if (args.length != 1 || args[0].equalsIgnoreCase("toggle")) {
            boolean isAdmin = playerManager.isAdmin(player);
            playerManager.toggleAdmin(player);
            player.sendMessage("§aAdmin mode " + (isAdmin ? "desactivado." : "activado."));
            return true;
        }

        return true;
    }
}
