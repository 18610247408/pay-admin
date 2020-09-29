package com.hhm.bussplat.agent.constant;

import com.hhm.bussplat.agent.util.RedisKeyUtil;

/**
 * redis 分布式锁key常量
 * @author q
 * @time 2020/5/18 9:00 上午
 */
public class RedisLockKeyConstant {
    /**
     * key的格式要求： string1:string2:string3
     * @see RedisKeyUtil 拼接方法
     */
    public static final String TEST_LOCK_PREFIX = "business:test";
}
