package net.turtton.simplemq.common.job;

public abstract class MessageQueue {
    final String key;
    final Redis redis;

    public MessageQueue(String key, Redis redis) {
        this.key = key;
        this.redis = redis;
    }
}
