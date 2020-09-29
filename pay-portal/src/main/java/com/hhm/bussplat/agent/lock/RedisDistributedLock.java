package com.hhm.bussplat.agent.lock;

import com.hhm.bussplat.agent.util.RedisUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

/**
 * Redis分布式锁
 *
 * @author q
 * @time 2020/5/15 11:06 下午
 */
@Component
public class RedisDistributedLock {
    private static final String UN_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     * 加锁
     *
     * @param key
     * @param token        锁的唯一标示
     * @param milliseconds 毫秒
     * @return token值，为null表示加锁失败
     */
    public static String tryLock(final String key, final String token, final Long milliseconds) {
        if (key == null || key.trim().equals("")
                || milliseconds == null || milliseconds < 1L) {
            return null;
        }
        String returnValue = null;
        if (token != null) {
            String value = RedisUtil.get(key);
            if (token.equals(value)) {
                //是自己持有的锁，保持可重入
                returnValue = token;
            }
        } else {
            String value = UUID.randomUUID().toString().replace("-", "");
            String result = RedisUtil.execute(new RedisUtil.CacheCallback<String>() {
                @Override
                public String doCallback(Jedis jedis) {
                    SetParams setParams = SetParams.setParams().nx().px(milliseconds);
                    return jedis.set(key, value, setParams);
                }
            });
            if ("OK".equals(result)) {
                returnValue = value;
            }
        }
        return returnValue;
    }

    /**
     * 解锁
     *
     * @param key
     * @param token
     * @return
     */
    public static boolean release(final String key, final String token) {
        if (key == null || key.trim().equals("") || token == null) {
            return false;
        }
        Object result = RedisUtil.execute(null, new RedisUtil.CacheCallback<Object>() {
            @Override
            public Object doCallback(Jedis jedis) {
                return jedis.eval(UN_LOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(token));
            }
        });
        if (result.equals(1L)) {
            return true;
        } else {
            return false;
        }
    }
}
