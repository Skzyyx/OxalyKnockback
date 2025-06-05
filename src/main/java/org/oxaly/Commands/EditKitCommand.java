package org.oxaly.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.oxaly.Interfaces.SubCommand;
import org.oxaly.Managers.ArenaManager;
import org.oxaly.Menus.EditKitMenu;
import org.oxaly.OxalyKnockback;

public class EditKitCommand implements SubCommand {

    private OxalyKnockback plugin;

    private final ArenaManager arenamanager;

    public EditKitCommand(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.arenamanager = plugin.getArenaManager();
    }

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public String getDescription() {
        return "Edit the inventory slots of the kit";
    }

    @Override
    public String getPermission() {
        return "kb.user.edit";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if(!(sender instanceof Player player)) return false;

        if(args.length != 0) return false;

        Location loc = player.getLocation().clone();

        if (!arenamanager.getCurrentArena().getSpawn().isInside(loc)) {
            player.sendMessage(Component.text("You need to be inside the spawn to use this command!"));
            return true;
        }

        player.openInventory(new EditKitMenu(player).getInventory());
        return true;
    }
}
