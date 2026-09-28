package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String URI = System.getenv("MONGODB_URI");
    private static final String DB_NAME = "meubanco";

    public static MongoDatabase getDatabase() {
        if (URI == null || URI.isBlank()) {
            throw new IllegalStateException("Defina a variável de ambiente MONGODB_URI.");
        }
        MongoClient client = MongoClients.create(URI);
        return client.getDatabase(DB_NAME);
    }
}
