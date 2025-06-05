package org.oxaly.Managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.oxaly.Commands.AdminComand;
import org.oxaly.Commands.ArenaCommand;
import org.oxaly.Commands.EditKitCommand;
import org.oxaly.Commands.ReloadCommand;
import org.oxaly.Interfaces.SubCommand;
import org.oxaly.OxalyKnockback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {

    private final OxalyKnockback plugin;
    private final Map<String, SubCommand> commands = new HashMap<>();

    public CommandManager(OxalyKnockback plugin) {
        this.plugin = plugin;
        registerInternalCommands();
    }

    private void registerInternalCommands() {
        registerCommand(new AdminComand(plugin));
        registerCommand(new ArenaCommand(plugin));
        registerCommand(new ReloadCommand(plugin));
        registerCommand(new EditKitCommand(plugin));
    }

    public void registerCommand(SubCommand command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public void register() {
        plugin.getCommand("kbadmin").setExecutor(this);
        plugin.getCommand("kb").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage("Usa /" + label + " <subcomando>");
            return true;
        }

        SubCommand sub = commands.get(args[0].toLowerCase());
        if (sub == null) {
            sender.sendMessage("Subcomando no encontrado");
            return true;
        }

        if (!sender.hasPermission(sub.getPermission())) {
            return false;
        }

        return sub.execute(sender, Arrays.copyOfRange(args, 1, args.length));
    }
}
