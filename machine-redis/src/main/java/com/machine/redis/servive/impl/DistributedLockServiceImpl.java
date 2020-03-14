package com.machine.redis.servive.impl;

import com.machine.redis.servive.IDistributedLockService;
import com.machine.redis.utils.Sha1Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class DistributedLockServiceImpl implements IDistributedLockService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*加锁脚本*/
    private static final String SCRIPT_LOCK = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('pexpire', KEYS[1], ARGV[2]) return 1 else return 0 end";
    /*解锁脚本*/
    private static final String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return -1 end";
    /*加锁脚本sha1值*/
    private static final String SCRIPT_LOCK_SHA1 = Sha1Util.encrypt(SCRIPT_LOCK);
    /*解锁脚本sha1值*/
    private static final String SCRIPT_UNLOCK_SHA1 = Sha1Util.encrypt(SCRIPT_UNLOCK);

    /**
     * @param redisTemplate
     * @param lockName
     * @param acquireTimeIntervalMilliseconds 当获取锁的频率(建议值:1-1000)
     * @param acquireTimeoutMilliseconds      最长获取锁的时间,超过时间则退出
     * @param lockTimeoutMilliseconds         锁的失效时间
     * @return 获得锁对应的唯一认证, 用于判断锁是否被修改
     */
    @Override
    public String acquireLock(RedisTemplate<String, Object> redisTemplate, final String lockName,
                              final int acquireTimeIntervalMilliseconds, final int acquireTimeoutMilliseconds,
                              final int lockTimeoutMilliseconds) {
        if (logger.isDebugEnabled()) {
            logger.debug("获取分布式redis锁,lockName:{}  acquireTimeIntervalMilliseconds:{}  " +
                            "acquireTimeoutMilliseconds:{}  lockTimeoutMilliseconds:{}",
                    lockName, acquireTimeIntervalMilliseconds, acquireTimeoutMilliseconds, lockTimeoutMilliseconds);
        }
        if (null == redisTemplate) {
            return null;
        }
        if (null == lockName || lockName.length() == 0) {
            return null;
        }
        if (acquireTimeIntervalMilliseconds <= 0) {
            return null;
        }
        if (acquireTimeoutMilliseconds <= 0) {
            return null;
        }
        if (lockTimeoutMilliseconds <= 0) {
            return null;
        }
        String identifier = UUID.randomUUID().toString().replace("-", "");
        Long endTime = System.currentTimeMillis() + acquireTimeoutMilliseconds;

        while (System.currentTimeMillis() < endTime) {
            Long result = redisTemplate.execute(new RedisScript<Long>() {
                                                    @Override
                                                    public String getSha1() {
                                                        return SCRIPT_LOCK_SHA1;
                                                    }

                                                    @Override
                                                    public Class<Long> getResultType() {
                                                        return Long.class;
                                                    }

                                                    @Override
                                                    public String getScriptAsString() {
                                                        return SCRIPT_LOCK;
                                                    }
                                                },
                    Collections.singletonList(lockName),// KEYS[1]
                    identifier, // ARGV[1]
                    lockTimeoutMilliseconds // ARGV[2]
            );
            if (0 == result) {
                try {
                    Thread.sleep(acquireTimeIntervalMilliseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return identifier;
            }
        }
        return null;
    }

    @Override
    public Integer releaseLock(RedisTemplate<String, Object> redisTemplate, String lockName, String identifier) {
        if (logger.isDebugEnabled()) {
            logger.debug("释放分布式redis锁,lockName:{}  identifier:{}", lockName, identifier);
        }
        if (null == lockName || lockName.length() == 0) {
            return 0;
        }
        if (null == identifier || identifier.length() == 0) {
            return 0;
        }
        Long result = redisTemplate.execute(new RedisScript<Long>() {
            @Override
            public String getSha1() {
                return SCRIPT_UNLOCK_SHA1;
            }

            @Override
            public Class<Long> getResultType() {
                return Long.class;
            }

            @Override
            public String getScriptAsString() {
                return SCRIPT_UNLOCK;
            }
        }, Collections.singletonList(lockName), identifier);
        return result.intValue();
    }
}
