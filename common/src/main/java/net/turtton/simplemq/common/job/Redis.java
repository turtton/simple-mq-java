package net.turtton.simplemq.common.job;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis implements AutoCloseable {
    JedisPool pool;

    public Redis() {
        var url = System.getenv("REDIS_URL");
        if (url == null) {
            url = "redis://localhost:6379";
        }
        pool = new JedisPool(url);
    }

    public Jedis getResource() {
        return pool.getResource();
    }

    @Override
    public void close() {
        pool.close();
    }
}
