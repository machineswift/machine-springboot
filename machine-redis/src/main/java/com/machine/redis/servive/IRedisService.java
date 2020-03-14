package com.machine.redis.servive;

/**
 * @Description: IRedisService
*/
public interface IRedisService {

	 void opsForValueSet(String key ,Object value) ;

	Object opsForValueGet(String key) ;
}
