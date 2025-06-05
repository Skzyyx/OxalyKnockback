package org.oxaly.Objects.Cosmetics;

public class InventorySlots {

    private Integer stickSlot;
    private Integer enderPearlSlot;
    private Integer blocksSlot;
    private Integer bowSlot;
    private Integer featherSlot;
    private Integer arrowSlot;

    public InventorySlots() {
        this.stickSlot = 0;
        this.enderPearlSlot = 1;
        this.blocksSlot = 2;
        this.bowSlot = 3;
        this.featherSlot = 4;
        this.arrowSlot = 8;
    }

    public InventorySlots(Integer stickSlot, Integer enderPearlSlot, Integer blocksSlot, Integer bowSlot, Integer featherSlot, Integer arrowSlot) {
        this.stickSlot = stickSlot;
        this.enderPearlSlot = enderPearlSlot;
        this.blocksSlot = blocksSlot;
        this.bowSlot = bowSlot;
        this.featherSlot = featherSlot;
        this.arrowSlot = arrowSlot;
    }

    public Integer getStickSlot() {
        return stickSlot;
    }

    public void setStickSlot(Integer stickSlot) {
        this.stickSlot = stickSlot;
    }

    public Integer getEnderPearlSlot() {
        return enderPearlSlot;
    }

    public void setEnderPearlSlot(Integer enderPearlSlot) {
        this.enderPearlSlot = enderPearlSlot;
    }

    public Integer getBlocksSlot() {
        return blocksSlot;
    }

    public void setBlocksSlot(Integer blocksSlot) {
        this.blocksSlot = blocksSlot;
    }

    public Integer getBowSlot() {
        return bowSlot;
    }

    public void setBowSlot(Integer bowSlot) {
        this.bowSlot = bowSlot;
    }

    public Integer getFeatherSlot() {
        return featherSlot;
    }

    public void setFeatherSlot(Integer featherSlot) {
        this.featherSlot = featherSlot;
    }

    public Integer getArrowSlot() {
        return arrowSlot;
    }

    public void setArrowSlot(Integer arrowSlot) {
        this.arrowSlot = arrowSlot;
    }

    @Override
    public String toString() {
        return "InventorySlots{" +
                "stickSlot=" + stickSlot +
                ", enderPearlSlot=" + enderPearlSlot +
                ", blocksSlot=" + blocksSlot +
                ", bowSlot=" + bowSlot +
                ", featherSlot=" + featherSlot +
                ", arrowSlot=" + arrowSlot +
                '}';
    }
}
