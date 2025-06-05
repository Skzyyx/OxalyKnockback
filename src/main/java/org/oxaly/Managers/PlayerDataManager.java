package org.oxaly.Managers;

import com.google.gson.Gson;
import org.oxaly.Connections.RedisConnection;
import org.oxaly.Objects.PlayerData;
import org.oxaly.Services.MongoPlayerDataService;
import org.oxaly.Services.RedisPlayerDataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.UnifiedJedis;

import java.util.Set;
import java.util.UUID;

public class PlayerDataManager {

    private static final Logger log = LoggerFactory.getLogger(PlayerDataManager.class);
    private final RedisPlayerDataCache redisCache;
    private final MongoPlayerDataService mongoService;
    private final Gson gson;

    public PlayerDataManager(RedisPlayerDataCache redisCache, MongoPlayerDataService mongoService) {
        this.redisCache = redisCache;
        this.mongoService = mongoService;
        this.gson = new Gson();
    }

    public PlayerData getPlayerData(UUID uuid) {
        PlayerData data = redisCache.load(uuid);
        if (data != null) {
            return data;
        }

        data = mongoService.load(uuid);
        if (data != null) {
            redisCache.save(data);
            log.info("PlayerData found in MongoDB and loaded into Redis for UUID: {}", uuid);
            return data;
        }

        // No existe, se crea por defecto
        PlayerData newData = new PlayerData();
        mongoService.save(newData);
        redisCache.save(newData);
        log.info("PlayerData not found in Redis or MongoDB. Created new data for UUID: {}", uuid);
        return newData;
    }

    public void save(PlayerData data) {
        mongoService.save(data);
        redisCache.save(data);
        log.info("Saved PlayerData for UUID: {} to MongoDB and Redis", data.getUuid());
    }

    /**
     * Transfiere todos los datos de Redis a MongoDB al desactivar el plugin.
     */
    public void flushRedisToMongo() {
        try (UnifiedJedis redis = RedisConnection.getConnection()) {
            Set<String> keys = redis.keys("playerdata:*");

            for (String key : keys) {
                String json = redis.get(key);
                if (json != null) {
                    try {
                        PlayerData data = gson.fromJson(json, PlayerData.class);
                        mongoService.save(data);
                    } catch (Exception e) {
                        log.error("Failed to parse or save PlayerData from Redis key: {}", key, e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to flush Redis data to MongoDB", e);
        }
    }
}
