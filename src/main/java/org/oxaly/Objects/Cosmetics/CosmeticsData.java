package org.oxaly.Objects.Cosmetics;

public class CosmeticsData {

    private BlocksData blocksData;
    private ParticlesData particlesData;
    private WeaponsData weaponsData;

    public CosmeticsData() {
        this.blocksData = new BlocksData();
        this.particlesData = new ParticlesData();
        this.weaponsData = new WeaponsData();
    }

    public CosmeticsData(BlocksData blocksData, ParticlesData particlesData, WeaponsData weaponsData) {
        this.blocksData = blocksData;
        this.particlesData = particlesData;
        this.weaponsData = weaponsData;
    }

    public BlocksData getBlocksData() {
        return blocksData;
    }

    public void setBlocksData(BlocksData blocksData) {
        this.blocksData = blocksData;
    }

    public ParticlesData getParticlesData() {
        return particlesData;
    }

    public void setParticlesData(ParticlesData particlesData) {
        this.particlesData = particlesData;
    }

    public WeaponsData getWeaponsData() {
        return weaponsData;
    }

    public void setWeaponsData(WeaponsData weaponsData) {
        this.weaponsData = weaponsData;
    }

    @Override
    public String toString() {
        return "CosmeticsData{" +
                "blocksData=" + blocksData +
                ", particlesData=" + particlesData +
                ", weaponsData=" + weaponsData +
                '}';
    }
}
