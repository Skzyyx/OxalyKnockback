package org.oxaly.Expansions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.oxaly.OxalyKnockback;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    private final OxalyKnockback plugin;

    public PlaceholderAPIExpansion(OxalyKnockback plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "kb";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Skzyy_";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {


        switch (params.toLowerCase()) {

            case "arena_current":
                return plugin.getArenaManager().getCurrentArena().getName();

            case "balance":
                var data = plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());
                if (data == null) return "Cargando..."; // o "Cargando..." o algún valor por defecto
                return data.getBalance().toString();
        }



        return "";
    }
}
