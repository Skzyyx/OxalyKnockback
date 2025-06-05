package org.oxaly.Commands;

import org.bukkit.command.CommandSender;
import org.oxaly.Interfaces.SubCommand;
import org.oxaly.Managers.ArenaManager;
import org.oxaly.Managers.PlayerManager;
import org.oxaly.OxalyKnockback;

public class ReloadCommand implements SubCommand {

    private final OxalyKnockback plugin;
    private final ArenaManager arenamanager;
    private final PlayerManager playermanager;

    public ReloadCommand(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.arenamanager = plugin.getArenaManager();
        this.playermanager = plugin.getPlayerManager();
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Recarga el plugin";
    }

    @Override
    public String getPermission() {
        return "kb.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        arenamanager.reloadArenas();
        sender.sendMessage("Arenas recargadas.");
        return true;
    }
}
