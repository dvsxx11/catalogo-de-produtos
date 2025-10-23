package controller;

import dao.ProdutoDAO;
import database.RedisConnection;
import model.Produto;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.ArrayList;


public class ProdutoController {
    private final ProdutoDAO dao = new ProdutoDAO();
    private final Jedis redis = RedisConnection.getConnection();

    public void cadastrarProduto(Produto p) {
        dao.create(p);
        redis.del("produtos_cache");
    }

    public List<Produto> listarProdutos() {
        String cache = redis.get("produtos_cache");
        if (cache != null) {
            return List.of(cache.split(";")).stream()
                    .map(s -> new Produto(s, 0, ""))
                    .toList();
        } else {
            List<Produto> produtos = dao.readAll();
            StringBuilder sb = new StringBuilder();
            for (Produto p : produtos) sb.append(p.getNome()).append(";");
            redis.set("produtos_cache", sb.toString());
            return produtos;
        }
    }

    public void atualizarProduto(String id, Produto p) {
        dao.update(id, p);
        redis.del("produtos_cache");
    }

    public void deletarProduto(String id) {
        dao.delete(id);
        redis.del("produtos_cache");
    }
}
