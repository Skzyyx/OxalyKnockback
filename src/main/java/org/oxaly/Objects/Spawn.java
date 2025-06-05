package org.oxaly.Objects;

import org.bukkit.Location;

public class Spawn {

    private Location pos1;
    private Location pos2;

    public Spawn() {
    }

    public Spawn(Location pos1, Location pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public boolean isInside(Location loc) {
        if (pos1 == null || pos2 == null || loc == null) return false;
        if (!pos1.getWorld().equals(loc.getWorld())) return false;

        double xMin = Math.min(pos1.getX(), pos2.getX());
        double xMax = Math.max(pos1.getX(), pos2.getX()) + 0.5;
        double yMin = Math.min(pos1.getY(), pos2.getY());
        double yMax = Math.max(pos1.getY(), pos2.getY()) + 0.5;
        double zMin = Math.min(pos1.getZ(), pos2.getZ());
        double zMax = Math.max(pos1.getZ(), pos2.getZ()) + 0.5;

        return loc.getX() >= xMin && loc.getX() <= xMax
                && loc.getY() >= yMin && loc.getY() <= yMax
                && loc.getZ() >= zMin && loc.getZ() <= zMax;
    }

    @Override
    public String toString() {
        return "Spawn{" +
                "pos1=" + pos1 +
                ", pos2=" + pos2 +
                '}';
    }
}
