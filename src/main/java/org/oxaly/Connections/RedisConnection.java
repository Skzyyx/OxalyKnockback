package org.oxaly.Connections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

public class RedisConnection {

    private static final Logger log = LoggerFactory.getLogger(RedisConnection.class);

    // Parámetros de conexión de Redis Cloud
    private static final String HOST = "redis-15392.c263.us-east-1-2.ec2.redns.redis-cloud.com";
    private static final int PORT = 15392;
    private static final String USER = "default";
    private static final String PASSWORD = "9IeYzn1S6a8RMpEYe7zmB0mAB45wQ6w8";

    private static UnifiedJedis jedis;

    private RedisConnection() {}

    /**
     * Inicializa la conexión con Redis usando UnifiedJedis (Redis Cloud).
     */
    public static synchronized void initialize() {
        if (jedis == null) {
            JedisClientConfig config = DefaultJedisClientConfig.builder()
                    .user(USER)
                    .password(PASSWORD)
                    .build();

            jedis = new UnifiedJedis(new HostAndPort(HOST, PORT), config);
            log.info("Redis Cloud connection established to {}:{}", HOST, PORT);
        }
    }

    /**
     * Obtiene la instancia única de UnifiedJedis.
     * @return instancia de UnifiedJedis
     */
    public static UnifiedJedis getConnection() {
        if (jedis == null) {
            initialize();
        }
        return jedis;
    }

    /**
     * Cierra la conexión al cerrar la aplicación.
     */
    public static void close() {
        if (jedis != null) {
            jedis.close();
            log.info("Redis Cloud connection closed.");
        }
    }
}
