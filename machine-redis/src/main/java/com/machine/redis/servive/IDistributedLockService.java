package com.machine.redis.servive;

import org.springframework.data.redis.core.RedisTemplate;

public interface IDistributedLockService {

    String acquireLock(RedisTemplate<String, Object> redisTemplate, final String lockName,
                       final int acquireTimeIntervalMilliseconds, final int acquireTimeoutMilliseconds,
                       final int lockTimeoutMilliseconds);

    Integer releaseLock(RedisTemplate<String, Object> redisTemplate, final String lockName, final String identifier);
}
