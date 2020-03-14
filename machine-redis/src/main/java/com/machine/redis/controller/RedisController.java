package com.machine.redis.controller;

import com.machine.redis.servive.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: RedisController
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private IRedisService redisService;

    /**
     * @Description: Browser[http://localhost:8080/machine/redis/getByKey?key=machine]
     */
    @RequestMapping(value = "/getByKey", method = RequestMethod.GET)
    @ResponseBody
    public Object getByKey(String key) {
        logger.debug("redis opsForValueGet,key:{}", key);
        return redisService.opsForValueGet(key);
    }

    /**
     * @Description: Browser[http://localhost:8080/machine/redis/setByKeyValue?key=machine&value=12345]
     */
    @RequestMapping(value = "/setByKeyValue", method = RequestMethod.GET)
    @ResponseBody
    public void setByKeyValue(String key, String value) {
        logger.info("redis opsForValueSet,key:{}   value:{}", key, value);
        redisService.opsForValueSet(key, value);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());
}
