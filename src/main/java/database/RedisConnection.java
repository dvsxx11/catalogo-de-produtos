package database;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static Jedis jedis;

    public static Jedis getConnection() {
        if (jedis == null) {
            jedis = new Jedis("redis-10102.c308.sa-east-1-1.ec2.redns.redis-cloud.com", 10102);
            jedis.auth("UCqGmstVKECWXtPkhDAocutr6nIJSJ28"); //
        }
        return jedis;
    }
}
