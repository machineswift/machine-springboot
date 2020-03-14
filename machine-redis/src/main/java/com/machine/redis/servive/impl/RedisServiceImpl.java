package com.machine.redis.servive.impl;

import com.machine.redis.servive.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: RedisServiceImpl
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplateTwo")
    private RedisTemplate<String, Object> redisTemplateTwo;


    public void opsForValueSet(String key, Object value) {

        redisTemplate.opsForValue().set(key, value);
    }

    public Object opsForValueGet(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
