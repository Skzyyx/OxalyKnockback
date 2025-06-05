package org.oxaly.Services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import org.oxaly.Connections.MongoConnection;
import org.oxaly.Objects.PlayerData;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class MongoPlayerDataService {

    private final MongoDatabase database;
    private final MongoCollection<PlayerData> collection;

    public MongoPlayerDataService() {
        this.database = MongoConnection.getDatabase();
        this.collection = database.getCollection("players", PlayerData.class);
    }

    public PlayerData load(UUID uuid) {
        return collection.find(eq("_id", uuid)).first();
    }

    public void save(PlayerData data) {
        collection.replaceOne(eq("_id", data.getUuid()), data, new ReplaceOptions().upsert(true));
    }

    public void delete(UUID uuid) {
        collection.deleteOne(eq("_id", uuid));
    }
}
