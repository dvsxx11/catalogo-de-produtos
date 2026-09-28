package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String DB_NAME = "meubanco";

    public static MongoDatabase getDatabase() {
        String uri = EnvLoader.get("MONGODB_URI");
        if (uri == null || uri.isBlank()) {
            throw new IllegalStateException("Defina a variável MONGODB_URI no arquivo .env ou como variável de ambiente.");
        }
        MongoClient client = MongoClients.create(uri);
        return client.getDatabase(DB_NAME);
    }
}
