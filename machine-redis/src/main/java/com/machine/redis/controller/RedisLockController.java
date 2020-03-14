package com.machine.redis.controller;

import com.machine.redis.servive.IDistributedLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: RedisController
 */
@RestController
@RequestMapping("/redisLock")
public class RedisLockController {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private IDistributedLockService distributedLockService;

    /**
     * @Description: Browser[http://localhost:8080/machine/redisLock/acquireLock?lockName=lock:machine]
     */
    @RequestMapping(value = "/acquireLock", method = RequestMethod.GET)
    @ResponseBody
    public Object acquireLock(String lockName) {
        return distributedLockService.acquireLock(redisTemplate, lockName,
                2, 10 * 1000, 100 * 1000);
    }

    /**
     * @Description: Browser[http://localhost:8080/machine/redisLock/releaseLock?lockName=lock:machine&identifier=lll]
     */
    @RequestMapping(value = "/releaseLock", method = RequestMethod.GET)
    @ResponseBody
    public Object releaseLock(String lockName, String identifier) {
        return distributedLockService.releaseLock(redisTemplate, lockName, identifier);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());
}
