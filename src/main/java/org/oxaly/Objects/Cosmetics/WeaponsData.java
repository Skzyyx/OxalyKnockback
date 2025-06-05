package org.oxaly.Objects.Cosmetics;

import org.oxaly.Cosmetics.Blocks;
import org.oxaly.Cosmetics.Weapons;

import java.util.ArrayList;
import java.util.List;

public class WeaponsData {

    private List<Weapons> ownedWeapons;
    private Weapons currentWeapon;

    public WeaponsData() {
        this.ownedWeapons = new ArrayList<>();
        ownedWeapons.add(Weapons.DEFAULT_WEAPON);
        currentWeapon = Weapons.DEFAULT_WEAPON;
    }

    public WeaponsData(List<Weapons> ownedWeapons, Weapons currentWeapon) {
        this.ownedWeapons = ownedWeapons;
        this.currentWeapon = currentWeapon;
    }

    public List<Weapons> getOwnedWeapons() {
        return ownedWeapons;
    }

    public void setOwnedWeapons(List<Weapons> ownedWeapons) {
        this.ownedWeapons = ownedWeapons;
    }

    public Weapons getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapons currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    @Override
    public String toString() {
        return "WeaponsData{" +
                "ownedWeapons=" + ownedWeapons +
                ", currentWeapon=" + currentWeapon +
                '}';
    }
}
