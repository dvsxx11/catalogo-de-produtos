package controller;

import dao.ProdutoDAO;
import database.RedisConnection;
import model.Produto;
import redis.clients.jedis.Jedis;
import java.util.List;

public class ProdutoController {
    private final ProdutoDAO dao = new ProdutoDAO();

    private Jedis getRedis() {
        return RedisConnection.getConnection();
    }

    public void cadastrarProduto(Produto p) {
        dao.create(p);
        Jedis redis = getRedis();
        if (redis != null) {
            try {
                redis.del("produtos_cache");
            } catch (Exception ignored) {
            }
        }
    }

    public List<Produto> listarProdutos() {
        Jedis redis = getRedis();
        if (redis != null) {
            try {
                String cache = redis.get("produtos_cache");
                if (cache != null && !cache.isBlank()) {
                    return List.of(cache.split(";")).stream()
                            .filter(s -> !s.isBlank())
                            .map(s -> new Produto(s, 0, ""))
                            .toList();
                }
            } catch (Exception ignored) {
            }
        }

        List<Produto> produtos = dao.readAll();
        if (redis != null) {
            try {
                StringBuilder sb = new StringBuilder();
                for (Produto p : produtos) {
                    sb.append(p.getNome()).append(";");
                }
                redis.set("produtos_cache", sb.toString());
            } catch (Exception ignored) {
            }
        }
        return produtos;
    }

    public void atualizarProduto(String id, Produto p) {
        dao.update(id, p);
        Jedis redis = getRedis();
        if (redis != null) {
            try {
                redis.del("produtos_cache");
            } catch (Exception ignored) {
            }
        }
    }

    public void deletarProduto(String id) {
        dao.delete(id);
        Jedis redis = getRedis();
        if (redis != null) {
            try {
                redis.del("produtos_cache");
            } catch (Exception ignored) {
            }
        }
    }
}
