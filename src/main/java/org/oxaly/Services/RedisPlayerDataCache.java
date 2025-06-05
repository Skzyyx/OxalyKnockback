package org.oxaly.Services;

import com.google.gson.Gson;
import org.oxaly.Connections.RedisConnection;
import org.oxaly.Objects.PlayerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.UnifiedJedis;

import java.util.UUID;

public class RedisPlayerDataCache {

    private static final Logger log = LoggerFactory.getLogger(RedisPlayerDataCache.class);

    private final Gson gson = new Gson();

    public PlayerData load(UUID uuid) {
        UnifiedJedis con = RedisConnection.getConnection();
        try {
            String json = con.get("playerdata:" + uuid);
            return json != null ? gson.fromJson(json, PlayerData.class) : null;
        } catch (Exception e) {
            log.error("Error reading from Redis: ", e);
            return null;
        }
    }

    public void save(PlayerData data) {
        UnifiedJedis con = RedisConnection.getConnection();
        try {
            con.set("playerdata:" + data.getUuid(), gson.toJson(data));
        } catch (Exception e) {
            log.error("Error saving on Redis: ", e);
        }
    }

    public void delete(UUID uuid) {
        UnifiedJedis con = RedisConnection.getConnection();
        try {
            con.del("playerdata:" + uuid);
        } catch (Exception e) {
            log.error("Error deleting from Redis: ", e);
        }
    }
}
