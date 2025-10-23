package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.MongoConnection;
import model.Produto;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProdutoDAO {
    private final MongoCollection<Document> collection;

    public ProdutoDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        this.collection = db.getCollection("produtos");
    }

    public void create(Produto p) {
        Document doc = new Document("nome", p.getNome())
                .append("preco", p.getPreco())
                .append("categoria", p.getCategoria());
        collection.insertOne(doc);
    }

    public List<Produto> readAll() {
        List<Produto> produtos = new ArrayList<>();
        for (Document d : collection.find()) {
            Number precoNum = (Number) d.get("preco");
            double preco = precoNum.doubleValue();

            produtos.add(new Produto(
                    d.getObjectId("_id").toString(),
                    d.getString("nome"),
                    preco,
                    d.getString("categoria")
            ));
        }
        return produtos;
    }

    public void update(String id, Produto p) {
        collection.updateOne(eq("_id", new ObjectId(id)),
                new Document("$set", new Document("nome", p.getNome())
                        .append("preco", p.getPreco())
                        .append("categoria", p.getCategoria())));
    }

    public void delete(String id) {
        collection.deleteOne(eq("_id", new ObjectId(id)));
    }
}
