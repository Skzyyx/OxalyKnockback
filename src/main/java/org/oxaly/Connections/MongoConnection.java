package org.oxaly.Connections;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoConnection {

    private static final Logger log = LoggerFactory.getLogger(MongoConnection.class);

    private static MongoClient mongoClient = null;

    // Connection string con usuario, contraseña y configuración del cluster
    private static final String URL = "mongodb+srv://joseislas252574:shgY4nEJ0XW3lAHd@cluster0.ejqrcav.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    // Nombre de la base de datos a la que quieres conectarte
    private static final String BD = "OxalyKBFFA";

    private MongoConnection() {}

    /**
     * Retorna la instancia Singleton de la clase.
     * Si la instancia aún no existe, se crea y establece la conexión
     * con la base de datos usando la configuración adecuada.
     * @return instancia de MongoDatabase.
     */
    public synchronized static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(new com.mongodb.ConnectionString(URL))
                    .codecRegistry(pojoCodecRegistry)
                    .build();

            mongoClient = MongoClients.create(clientSettings);
            log.info("Connected to MongoDB at cluster URL, database: " + BD);
            return mongoClient.getDatabase(BD).withCodecRegistry(pojoCodecRegistry);
        }

        return mongoClient.getDatabase(BD);
    }
}
