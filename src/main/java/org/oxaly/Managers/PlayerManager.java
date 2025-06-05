package org.oxaly.Managers;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.oxaly.OxalyKnockback;

import java.util.HashSet;

public class PlayerManager {


    private final HashSet<Player> admins;

    public PlayerManager() {
        this.admins = new HashSet<>();
    }

    public void toggleAdmin(Player p) {
        if (!admins.contains(p)) {
            admins.add(p);
            p.sendActionBar(Component.text("Admin mode enabled", NamedTextColor.GREEN));
            return;
        }
        admins.remove(p);
        p.sendActionBar(Component.text("Admin mode disabled", NamedTextColor.RED));
    }

    public boolean isAdmin(Player p) {
        return admins.contains(p);
    }

    public void tpAllSpawn(Location loc) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (isAdmin(p)) continue;

            p.teleport(loc);
        }
    }
}
