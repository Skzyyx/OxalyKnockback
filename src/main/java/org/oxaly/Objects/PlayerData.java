package org.oxaly.Objects;

import org.bson.types.ObjectId;
import org.oxaly.Objects.Cosmetics.CosmeticsData;
import org.oxaly.Objects.Cosmetics.InventorySlots;

public class PlayerData {

    private ObjectId id;
    private String uuid;
    private String name;
    private Double balance;
    private Integer kills;
    private Integer deaths;
    private InventorySlots inventorySlots;

    private CosmeticsData cosmeticsData;

    public PlayerData() {
    }

    public PlayerData(ObjectId id, String uuid, String name, Double balance, Integer kills, Integer deaths, InventorySlots inventorySlots, CosmeticsData cosmeticsData) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.balance = balance;
        this.kills = kills;
        this.deaths = deaths;
        this.inventorySlots = inventorySlots;
        this.cosmeticsData = cosmeticsData;
    }

    public PlayerData(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.balance = 0.0;
        this.kills = 0;
        this.deaths = 0;
        this.inventorySlots = new InventorySlots();
        this.cosmeticsData = new CosmeticsData();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public InventorySlots getInventorySlots() {
        return inventorySlots;
    }

    public void setInventorySlots(InventorySlots inventorySlots) {
        this.inventorySlots = inventorySlots;
    }

    public CosmeticsData getCosmeticsData() {
        return cosmeticsData;
    }

    public void setCosmeticsData(CosmeticsData cosmeticsData) {
        this.cosmeticsData = cosmeticsData;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", inventorySlots=" + inventorySlots +
                ", cosmeticsData=" + cosmeticsData +
                '}';
    }
}
