package database;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static Jedis jedis;
    private static boolean connectionAttempted = false;

    public static Jedis getConnection() {
        if (!connectionAttempted) {
            connectionAttempted = true;
            String host = EnvLoader.get("REDIS_HOST");
            if (host == null || host.isBlank()) {
                host = "redis-10102.c308.sa-east-1-1.ec2.redns.redis-cloud.com";
            }

            String portStr = EnvLoader.get("REDIS_PORT");
            int port = 6379;
            if (portStr != null && !portStr.isBlank()) {
                try {
                    port = Integer.parseInt(portStr);
                } catch (NumberFormatException ignored) {
                }
            } else if (host.contains("redis-cloud.com")) {
                port = 10102;
            }

            String password = EnvLoader.get("REDIS_PASSWORD");

            try {
                Jedis client = new Jedis(host, port, 2000); // 2 segundos de timeout
                if (password != null && !password.isBlank()) {
                    client.auth(password);
                }
                client.ping();
                jedis = client;
                System.out.println("Redis conectado com sucesso!");
            } catch (Exception e) {
                System.out.println("Aviso: Não foi possível conectar ao Redis (" + e.getMessage() + "). O sistema funcionará sem cache.");
                jedis = null;
            }
        }
        return jedis;
    }
}
