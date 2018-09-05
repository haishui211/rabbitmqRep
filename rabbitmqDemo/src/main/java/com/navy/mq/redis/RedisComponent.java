package com.navy.mq.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisComponent")
public class RedisComponent {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public void saveObj(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}
	
	public void saveObj(String key, Object obj, long expireTime) {
		redisTemplate.opsForValue().set(key, obj, expireTime, TimeUnit.SECONDS);
	}
	
	public Object getObj(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public boolean delete(String key){
		return redisTemplate.delete(key);
	}
	
	public Set<String> getAllKeys(){
		return redisTemplate.keys("*");
	}
	
	public long increment(String key){
		return redisTemplate.opsForValue().increment(key, 1);
	}
	
	public void saveWithSet(String key, String value) {
		stringRedisTemplate.opsForSet().add(key, value);
	}
	
	public Set<String> getFromSet(String key) {
		return stringRedisTemplate.opsForSet().members(key);
	}
	
	public boolean isContain(String key, String value) {
		return stringRedisTemplate.opsForSet().isMember(key, value);
	}
	
	public void saveWithList(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
	}
	
	public Object getOneFromList(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}
}
