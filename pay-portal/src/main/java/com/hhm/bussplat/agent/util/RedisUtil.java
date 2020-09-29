package com.hhm.bussplat.agent.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author q
 * @time 2020/5/15 11:28 下午
 */
@Component
@Slf4j
public class RedisUtil {
    private static JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        RedisUtil.jedisPool = jedisPool;
    }

    public static Boolean exists(final String key) {
        checkKey(key);
        return execute(jedis -> jedis.exists(key));
    }

    public static Boolean exists(final byte[] key) {
        checkKey(key);
        return execute(jedis -> jedis.exists(key));
    }

    public static Long expire(final String key, final int seconds) {
        checkKey(key);
        return execute(jedis -> jedis.expire(key,seconds));
    }

    public static Long expire(final byte[] key, final int seconds) {
        checkKey(key);
        return execute(jedis -> jedis.expire(key,seconds));
    }

    public static Long del(final String key) {
        checkKey(key);
        return execute(jedis -> jedis.del(key));
    }

    public static Long del(final byte[] key) {
        checkKey(key);
        return execute(jedis -> jedis.del(key));
    }

    public static long ttl(final String key){
        checkKey(key);
        return execute(jedis -> jedis.ttl(key));
    }

    public static long ttl(final byte[] key){
        checkKey(key);
        return execute(jedis -> jedis.ttl(key));
    }

    //================================================= String String=================================================
    public static void set(final String key,final String value) {
        checkKey(key);
        execute(jedis -> jedis.set(key,value));
    }

    public static String get(final String key) {
        checkKey(key);
        return execute(jedis -> jedis.get(key));
    }

    public static void setEx(final String key,final String value,final int seconds) {
        checkKey(key);
        execute(jedis -> jedis.setex(key,seconds,value));
    }

    public static Long setnx(final String key, final String value) {
        checkKey(key);
        return execute(jedis -> jedis.setnx(key,value));
    }

    public static Long incr(final String key) {
        checkKey(key);
        return execute(jedis -> jedis.incr(key));
    }

    public static Long incrby(final String key,final Long increment) {
        checkKey(key);
        return execute(jedis -> jedis.incrBy(key,increment));
    }

    //================================================= String byte[]=================================================

    public static void set(final byte[] key,final Object value) {
        checkKey(key);
        execute(jedis -> jedis.set(key,serialize(value)));
    }

    public static <T> T get(final byte[] key,final Class<T> clazz) {
        checkKey(key);
        return (T)deserialize(execute(jedis -> jedis.get(key)));
    }

    public static void setEx(final byte[] key,final Object value,final int seconds) {
        checkKey(key);
        execute(jedis -> jedis.setex(key,seconds,serialize(value)));
    }

    public static Long setnx(final byte[] key, final Object value) {
        checkKey(key);
        return execute(jedis -> jedis.setnx(key,serialize(value)));
    }

    public static Long incr(final byte[] key) {
        checkKey(key);
        return execute(jedis -> jedis.incr(key));
    }

    public static Long incrby(final byte[] key,final Long increment) {
        checkKey(key);
        return execute(jedis -> jedis.incrBy(key,increment));
    }

    //================================================= Set String=================================================

    public static Long sadd(final String key,final String... member) {
        checkKey(key);
        return execute(jedis -> jedis.sadd(key,member));
    }

    public static Long srem(final String key,final String member) {
        checkKey(key);
        return execute(jedis -> jedis.srem(key,member));
    }

    public static Set<String> smembers(final String key){
        checkKey(key);
        return execute(jedis -> jedis.smembers(key));
    }

    public static Boolean sismember(final String key,final String member){
        checkKey(key);
        return execute(jedis -> jedis.sismember(key,member));
    }

    public static Long scard(final String key) {
        checkKey(key);
        return execute(jedis -> jedis.scard(key));
    }

    //================================================= Set byte[]=================================================

    public static Long sadd(final byte[] key,final byte[]... member) {
        checkKey(key);
        return execute(jedis -> jedis.sadd(key,member));
    }

    public static Long srem(final byte[] key,final byte[] member) {
        checkKey(key);
        return execute(jedis -> jedis.srem(key,member));
    }

    public static Set<byte[]> smembers(final byte[] key){
        checkKey(key);
        return execute(jedis -> jedis.smembers(key));
    }

    public static Boolean sismember(final byte[] key,final byte[] member){
        checkKey(key);
        return execute(jedis -> jedis.sismember(key,member));
    }

    public static Long scard(final byte[] key) {
        checkKey(key);
        return execute(jedis -> jedis.scard(key));
    }

    //================================================= 有序 Set String=================================================

    public static Long zadd(final String key, final double score, final String member) {
        checkKey(key);
        return execute(jedis -> jedis.zadd(key,score,member));
    }

    public static Long zrem(final String key, final String... members) {
        checkKey(key);
        return execute(jedis -> jedis.zrem(key,members));
    }

    public static Set<String> zrevrange(final String key, final int start, final int end) {
        checkKey(key);
        return execute(jedis -> jedis.zrevrange(key,start,end));
    }

    public static Set<String> zrangeByScore(final String key, final double min, final double max) {
        checkKey(key);
        return execute(jedis -> jedis.zrangeByScore(key,min,max));
    }

    public static Set<String> zrange(final String key, final int start, final int end) {
        checkKey(key);
        return execute(jedis -> jedis.zrange(key,start,end));
    }

    //================================================= 有序 Set byte[]=================================================

    public static Long zadd(final byte[] key, final double score, final byte[] member) {
        checkKey(key);
        return execute(jedis -> jedis.zadd(key,score,member));
    }

    public static Long zrem(final byte[] key, final byte[]... members) {
        checkKey(key);
        return execute(jedis -> jedis.zrem(key,members));
    }

    public static Set<byte[]> zrevrange(final byte[] key, final int start, final int end) {
        checkKey(key);
        return execute(jedis -> jedis.zrevrange(key,start,end));
    }

    public static Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max) {
        checkKey(key);
        return execute(jedis -> jedis.zrangeByScore(key,min,max));
    }

    public static Set<byte[]> zrange(final byte[] key, final int start, final int end) {
        checkKey(key);
        return execute(jedis -> jedis.zrange(key,start,end));
    }

    //================================================= List String=================================================
    public static Long rpush(final String key,final String ... vals){
        checkKey(key);
        return execute(jedis -> jedis.rpush(key,vals));
    }

    public static Long lpush(final String key,final String ... vals){
        checkKey(key);
        return execute(jedis -> jedis.lpush(key,vals));
    }

    public static String lpop(final String key){
        checkKey(key);
        return execute(jedis -> jedis.lpop(key));
    }

    public static Long llen(final String key){
        checkKey(key);
        return execute(jedis -> jedis.llen(key));
    }

    public static List<String> lrange(final String key,final long start,final long end){
        checkKey(key);
        return execute(jedis -> jedis.lrange(key,start,end));
    }

    //================================================= List byte[]=================================================
    public static Long rpush(final byte[] key,final byte[] ... vals){
        checkKey(key);
        return execute(jedis -> jedis.rpush(key,vals));
    }

    public static Long lpush(final byte[] key,final byte[] ... vals){
        checkKey(key);
        return execute(jedis -> jedis.lpush(key,vals));
    }

    public static byte[] lpop(final byte[] key){
        checkKey(key);
        return execute(jedis -> jedis.lpop(key));
    }

    public static Long llen(final byte[] key){
        checkKey(key);
        return execute(jedis -> jedis.llen(key));
    }

    public static List<byte[]> lrange(final byte[] key,final long start,final long end){
        checkKey(key);
        return execute(jedis -> jedis.lrange(key,start,end));
    }

    //================================================= Hash String=================================================
    public static Long hset(final String key,final String field,final String value){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hset(key, field, value));
    }

    public static String hget(final String key,final String field){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hget(key,field));
    }

    public static Long hdel(final String key,final  String field) {
        checkHashParams(key,field);
        return execute(jedis -> jedis.hdel(key,field));
    }

    public static Map<String, String> hgetAll(final String key) {
        checkKey(key);
        return execute(jedis -> jedis.hgetAll(key));
    }

    public static boolean hexists(final String key,final String field){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hexists(key,field));
    }

    public static Double hincrByFloat(final String key,final String field,final Double value){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hincrByFloat(key,field,value));
    }
    //================================================= Hash byte[]=================================================

    public static Long hset(final byte[] key,final byte[] field,final Object value){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hset(key, field, serialize(value)));
    }

    public static byte[] hget(final byte[] key,final byte[] field){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hget(key,field));
    }

    public static Long hdel(final byte[] key,final  byte[] field) {
        checkHashParams(key,field);
        return execute(jedis -> jedis.hdel(key,field));
    }

    public static Map<byte[], byte[]> hgetAll(final byte[] key) {
        checkKey(key);
        return execute(jedis -> jedis.hgetAll(key));
    }

    public static boolean hexists(final byte[] key,final byte[] field){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hexists(key,field));
    }

    public static Double hincrByFloat(final byte[] key,final byte[] field,final Double value){
        checkHashParams(key,field);
        return execute(jedis -> jedis.hincrByFloat(key,field,value));
    }

    /**
     * jdk序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        if(object == null) {
            return new byte[0];
        } else if(!(object instanceof Serializable)) {
            throw new IllegalArgumentException(object.getClass().getSimpleName() + " requires a Serializable payload " + "but received an object of type [" + object.getClass().getName() + "]");
        } else {
            try {
                ByteArrayOutputStream e = new ByteArrayOutputStream(128);
                Throwable var2 = null;

                byte[] var4;
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(e);
                    oos.writeObject(object);
                    oos.flush();
                    var4 = e.toByteArray();
                } catch (Throwable var14) {
                    var2 = var14;
                    throw var14;
                } finally {
                    if(e != null) {
                        if(var2 != null) {
                            try {
                                e.close();
                            } catch (Throwable var13) {
                                var2.addSuppressed(var13);
                            }
                        } else {
                            e.close();
                        }
                    }

                }

                return var4;
            } catch (Throwable var16) {
                throw new RuntimeException("Failed to serialize object using " + object.getClass().getSimpleName(), var16);
            }
        }
    }

    /**
     * jdk反序列化
     * @param data
     * @return
     */
    public static Object deserialize(byte[] data) {
        if(data != null && data.length != 0) {
            try {
                ByteArrayInputStream e = new ByteArrayInputStream(data);
                Throwable var2 = null;

                Object var4;
                try {
                    ObjectInputStream ois = new ObjectInputStream(e);
                    var4 = ois.readObject();
                } catch (Throwable var14) {
                    var2 = var14;
                    throw var14;
                } finally {
                    if(e != null) {
                        if(var2 != null) {
                            try {
                                e.close();
                            } catch (Throwable var13) {
                                var2.addSuppressed(var13);
                            }
                        } else {
                            e.close();
                        }
                    }

                }

                return var4;
            } catch (Throwable var16) {
                throw new RuntimeException("Failed to deserialize payload. Is the byte array a result of corresponding serialization for Class ?", var16);
            }
        } else {
            return null;
        }
    }

    /**
     * 检查hash类型参数的合法性
     * @param key
     * @param field
     * @return
     */
    private static void checkHashParams(String key, String field){
        if(key == null || field == null || key.trim().equals("") || field.trim().equals(""))
            throw new IllegalArgumentException("cache key or field is blank");
    }

    /**
     * 检查hash类型参数的合法性
     * @param key
     * @param field
     * @return
     */
    private static void checkHashParams(byte[] key, byte[] field){
        if(key == null || key.length <= 0 || field == null || field.length <= 0)
            throw new IllegalArgumentException("cache key or field is blank");
    }

    /**
     * 检查key的合法性
     * @param key
     */
    private static void checkKey(String key){
        if(key == null || "".equals(key.trim()))
            throw new IllegalArgumentException("cache key is blank");
    }

    /**
     * 检查key的合法性
     * @param key
     */
    private static void checkKey(byte[] key) {
        if(key == null || key.length <= 0)
            throw new IllegalArgumentException("cache key is blank");
    }

    public static <T> T execute(Integer dbIndex, CacheCallback<T> callback) {
        Jedis jedis = jedisPool.getResource();
        T e;
        try {
            if (dbIndex != null) {
                jedis.select(dbIndex.intValue());
            }
            e = callback.doCallback(jedis);
        } catch (JedisConnectionException var15) {
            log.error("", var15);
            throw var15;
        } catch (Exception var16) {
            log.error("", var16);
            throw new RuntimeException(var16);
        } finally {
            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    var13.printStackTrace();
                }
            }
        }
        return e;
    }

    public static <T> T execute(CacheCallback<T> callback) {
        Jedis jedis = jedisPool.getResource();
        T e;
        try {
            e = callback.doCallback(jedis);
        } catch (JedisConnectionException var14) {
            log.error("", var14);
            throw var14;
        } catch (Exception var15) {
            log.error("", var15);
            throw new RuntimeException(var15);
        } finally {
            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    var12.printStackTrace();
                }
            }
        }
        return e;
    }

    @FunctionalInterface
    public interface CacheCallback<T> {
        T doCallback(Jedis jedis);
    }
}
