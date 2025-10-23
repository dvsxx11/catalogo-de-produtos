package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String URI = "mongodb+srv://dvsxx11:objetivo1701@cluster1.lcolknj.mongodb.net/meubanco?retryWrites=true&w=majority";
    private static final String DB_NAME = "meubanco";

    public static MongoDatabase getDatabase() {
        MongoClient client = MongoClients.create(URI);
        return client.getDatabase(DB_NAME);
    }
}
