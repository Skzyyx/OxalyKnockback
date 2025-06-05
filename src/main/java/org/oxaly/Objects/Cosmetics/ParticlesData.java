package org.oxaly.Objects.Cosmetics;

import org.oxaly.Cosmetics.Particles;

import java.util.ArrayList;
import java.util.List;

public class ParticlesData {

    private List<Particles> ownedParticles;
    private Particles currentParticle;

    public ParticlesData() {
        this.ownedParticles = new ArrayList<>();
        this.currentParticle = null;
    }

    public ParticlesData(List<Particles> ownedParticles, Particles currentParticle) {
        this.ownedParticles = ownedParticles;
        this.currentParticle = currentParticle;
    }

    public Particles getCurrentParticle() {
        return currentParticle;
    }

    public void setCurrentParticle(Particles currentParticle) {
        this.currentParticle = currentParticle;
    }

    public List<Particles> getOwnedParticles() {
        return ownedParticles;
    }

    public void setOwnedParticles(List<Particles> ownedParticles) {
        this.ownedParticles = ownedParticles;
    }

    @Override
    public String toString() {
        return "ParticlesData{" +
                "ownedParticles=" + ownedParticles +
                ", currentParticle=" + currentParticle +
                '}';
    }
}
