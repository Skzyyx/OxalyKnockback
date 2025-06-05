package org.oxaly.Interfaces;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    String getName();
    String getDescription();
    String getPermission();
    boolean execute(CommandSender sender, String[] args);
}
