package org.oxaly.Objects.Cosmetics;

import org.oxaly.Cosmetics.Blocks;
import org.oxaly.Cosmetics.Particles;

import java.util.ArrayList;
import java.util.List;

public class BlocksData {

    private List<Blocks> ownedBlocks;
    private Blocks currentBlock;

    public BlocksData() {
        this.ownedBlocks = new ArrayList<>();
        ownedBlocks.add(Blocks.DEFAULT_BLOCK);
        currentBlock = Blocks.DEFAULT_BLOCK;
    }

    public BlocksData(List<Blocks> ownedBlocks, Blocks currentBlock) {
        this.ownedBlocks = ownedBlocks;
        this.currentBlock = currentBlock;
    }

    public List<Blocks> getOwnedBlocks() {
        return ownedBlocks;
    }

    public void setOwnedBlocks(List<Blocks> ownedBlocks) {
        this.ownedBlocks = ownedBlocks;
    }

    public Blocks getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Blocks currentBlock) {
        this.currentBlock = currentBlock;
    }

    @Override
    public String toString() {
        return "BlocksData{" +
                "ownedBlocks=" + ownedBlocks +
                ", currentBlock=" + currentBlock +
                '}';
    }
}
