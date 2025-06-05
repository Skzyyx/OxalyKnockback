package org.oxaly.Listeners;

import org.bukkit.event.Listener;
import org.oxaly.Managers.PlayerDataManager;
import org.oxaly.OxalyKnockback;

public class PlayerDataListener implements Listener {

    private final OxalyKnockback plugin;

    private final PlayerDataManager playerDataManager;

    public PlayerDataListener(OxalyKnockback plugin) {
        this.plugin = plugin;
        this.playerDataManager = this.plugin.getPlayerDataManager();
    }

}
