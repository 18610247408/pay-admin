package com.hhm.bussplat.agent.util;

/**
 * redis key相关工具类
 * @author q
 * @time 2020/5/18 9:03 上午
 */
public class RedisKeyUtil {
    /**
     * 拼接key
     * @param prefix
     * @param params
     * @return
     */
    public static String spliceKey(String prefix,String ... params){
        if(prefix == null || "".equals(prefix.trim()))
            throw new IllegalArgumentException("prefix don`t be null or empty");
        if(params == null || params.length <= 0){
            return prefix;
        }
        StringBuffer sb = new StringBuffer(prefix);
        for (int i = 0; i < params.length;i++){
            sb.append(":").append(params[i]);
        }
        return sb.toString();
    }
}
