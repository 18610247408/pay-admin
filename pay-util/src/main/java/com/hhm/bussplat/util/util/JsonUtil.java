package com.hhm.bussplat.util.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * JSON处理工具类
 *
 * @author q
 * @time 2020/5/15 10:57 下午
 */
public class JsonUtil {
    private static volatile ObjectMapper mapper = new ObjectMapper();

    private JsonUtil(){}

    public static byte[] toJsonBytes(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (IOException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(byte[] json, Class<T> clazz) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        } else {
            try {
                return mapper.readValue(json, typeReference);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public static <T> T toObject(byte[] json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        } else {
            try {
                return mapper.readValue(json, typeReference);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public static String toJson(Object o) {
        if (o == null) {
            return null;
        } else {
            try {
                return mapper.writeValueAsString(o);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }
    
    public static <T> T toObject(Map<String,Object> map, Class<T> clazz) {
        if (map == null) return null;
        return mapper.convertValue(map, clazz);
    }
}
