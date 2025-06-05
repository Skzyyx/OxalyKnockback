package org.oxaly.Cosmetics;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.api.particle.ParticleList_1_13;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.oxaly.OxalyKnockback;

import java.util.function.BiConsumer;

public class ParticleEffectLibrary {

    public static BiConsumer<Player, Integer> circleEffect(Particles particle) {
        return (player, ticks) -> {
            Location base = player.getLocation().add(0, 1.5, 0);
            for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 8) {
                double x = Math.cos(angle);
                double z = Math.sin(angle);
                Location loc = base.clone().add(x, 0, z);
                particle.send(player, loc);
            }
        };
    }

    public static BiConsumer<Player, Integer> spiralEffect(Particles particle) {
        return (player, ticks) -> {
            Location base = player.getLocation().add(0, 0.5, 0);
            double t = ticks * 0.1;
            double x = Math.cos(t) * 1;
            double y = t * 0.1;
            double z = Math.sin(t) * 1;
            Location loc = base.clone().add(x, y % 2, z);
            particle.send(player, loc);
        };
    }

    public static BiConsumer<Player, Integer> helixEffect(Particles particle) {
        return (player, ticks) -> {
            Location base = player.getLocation().add(0, 0.5, 0);
            for (double y = 0; y <= 2; y += 0.2) {
                double angle = ticks * 0.15 + y * 4;
                double x = Math.cos(angle);
                double z = Math.sin(angle);
                Location loc = base.clone().add(x, y, z);
                particle.send(player, loc);
            }
        };
    }

    public static BiConsumer<Player, Integer> feetAuraEffect(Particles particle) {
        return (player, ticks) -> {
            Location base = player.getLocation().add(0, 0.1, 0);
            for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 6) {
                double x = Math.cos(angle) * 0.4;
                double z = Math.sin(angle) * 0.4;
                Location loc = base.clone().add(x, 0, z);
                particle.send(player, loc);
            }
        };
    }

    public static BiConsumer<Player, Integer> butterflyEffect(Particles particle) {
        return (player, ticks) -> {
            Location location = player.getLocation().clone();

            final boolean o = false;
            final boolean x = true;
            final boolean[][] shape = {
                    {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
                    {o, x, x, x, x, o, o, o, o, o, o, o, x, x, x, x, o, o},
                    {o, o, x, x, x, x, x, o, o, o, x, x, x, x, x, o, o, o},
                    {o, o, o, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o},
                    {o, o, o, o, x, x, x, x, x, x, x, x, x, o, o, o, o, o},
                    {o, o, o, o, x, x, x, x, o, x, x, x, x, o, o, o, o, o},
                    {o, o, o, o, o, x, x, x, o, x, x, x, o, o, o, o, o, o},
                    {o, o, o, o, o, x, x, o, o, o, x, x, o, o, o, o, o, o},
                    {o, o, o, o, x, x, o, o, o, o, o, x, x, o, o, o, o, o}
            };

            double space = 0.2;
            double defX = location.getX() - (space * shape[0].length / 2) + space;
            double x1 = defX;
            double y = location.getY() + 2;
            double angle = -((location.getYaw() + 180) / 60);
            angle += (location.getYaw() < -180 ? 3.25 : 2.985);

            for (boolean[] row : shape) {
                for (boolean pixel : row) {
                    if (pixel) {
                        Location target = location.clone();
                        target.setX(x1);
                        target.setY(y);

                        Vector v = target.toVector().subtract(location.toVector());
                        v = rotateAroundAxisY(v, angle);

                        Vector v2 = getBackVector(location);
                        v2.setY(0).multiply(-0.2);

                        location.add(v);
                        location.add(v2);
                        particle.send(player, location);
                        location.subtract(v2);
                        location.subtract(v);
                    }
                    x1 += space;
                }
                y -= space;
                x1 = defX;
            }
        };
    }

    public static BiConsumer<Player, Integer> enchantTableEffect(Particles particle) {
        return (player, ticks) -> {
            if (ticks % 2 != 0) return;
            double y = Math.random() * 2;
            Location loc = player.getLocation().add(0, 0.1 + y, 0);
            for (int i = 0; i < 5; i++) {
                particle.send(player, loc);
            }
        };
    }

    public static BiConsumer<Player, Integer> superHeroEffect(Particles particle) {
        return (player, ticks) -> {
            Location location = player.getLocation().clone();
            final boolean x = true;
            final boolean[][] shape = {
                    {x, x, x, x, x},
                    {x, x, x, x, x},
                    {x, x, x, x, x},
                    {x, x, x, x, x},
                    {x, x, x, x, x},
                    {x, x, x, x, x},
                    {x, x, x, x, x},
                    {x, x, x, x, x}
            };

            double space = 0.2;
            double defX = location.getX() - (space * shape[0].length / 2) + space / 2;
            double xPos = defX;
            double defY = location.getY() + 1.5;
            double y = defY;
            double angle = -((location.getYaw() + 180) / 60);
            angle += (location.getYaw() < -180 ? 3.25 : 2.985);

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j]) {
                        Location target = location.clone();
                        target.setX(xPos);
                        target.setY(y);

                        Vector v = target.toVector().subtract(location.toVector());
                        Vector v2 = getBackVector(location);
                        v = rotateAroundAxisY(v, angle);
                        double iT = ((double) i) / 10;
                        v2.setY(0).multiply(-0.2 - iT);

                        Location loc = location.clone();
                        loc.add(v);
                        loc.add(v2);
                        particle.send(player, loc);
                        loc.subtract(v2);
                        loc.subtract(v);
                    }
                    xPos += space;
                }
                y -= space;
                xPos = defX;
            }
        };
    }

    private static Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    private static Vector getBackVector(Location loc) {
        final double rads = Math.toRadians(loc.getYaw() + 90);
        return new Vector(Math.cos(rads), 0, Math.sin(rads));
    }
}
