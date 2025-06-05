package org.oxaly.Objects;

import org.bukkit.Location;

public class Arena {

    private String name;
    private Location location;
    private Spawn spawn;

    public Arena() {
    }

    public Arena(String name, Location location, Spawn spawn) {
        this.name = name;
        this.location = location;
        this.spawn = spawn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Spawn getSpawn() {
        return spawn;
    }

    public void setSpawn(Spawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public String toString() {
        return "Arena{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", spawn=" + spawn +
                '}';
    }
}
