package sddtc.fanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 *
 * Redis data
 * Created by sddtc on 15/11/11.
 */
@Service
public class RedisServiceImpl {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * get value
     * @param key redis-key
     * @return
     */
    public String get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        return valueOperations.get(key);
    }

    /**
     * set value of key
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(key, value);
    }
}
