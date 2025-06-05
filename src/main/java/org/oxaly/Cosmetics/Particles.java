package org.oxaly.Cosmetics;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.oxaly.OxalyKnockback;

import java.util.function.BiConsumer;

public enum Particles {

    ANGEL_RING("angel_ring") {
        @Override
        public void send(Player player, Location location) {
            // Efecto puntual al equipar
            ParticleNativeCore.loadAPI(OxalyKnockback.getInstance()).LIST_1_13.END_ROD.packet(true, location).sendTo(player);
        }

        @Override
        public BiConsumer<Player, Integer> getEffectFunction() {
            return ParticleEffectLibrary.circleEffect(this);
        }
    },

    FLAME_CIRCLE("flame_circle") {
        @Override
        public void send(Player player, Location location) {
            // Efecto puntual al equipar
            ParticleNativeCore.loadAPI(OxalyKnockback.getInstance()).LIST_1_13.END_ROD.packet(true, location).sendTo(player);
        }

        @Override
        public BiConsumer<Player, Integer> getEffectFunction() {
            return ParticleEffectLibrary.spiralEffect(this);
        }
    };

    private final String id;

    Particles(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // Metodo abstracto que cada cosmético debe implementar
    public abstract void send(Player player, Location location);
    public abstract BiConsumer<Player, Integer> getEffectFunction();

    public static Particles fromId(String id) {
        for (Particles type : values()) {
            if (type.getId().equalsIgnoreCase(id)) return type;
        }
        return null;
    }
}
