package com.zcq.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.it235.manager.Knife4jRedisManager;
import com.github.it235.util.RedisValUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ParseRedisUtil {
    @Autowired
    private Knife4jRedisManager knife4jRedisManager;

    @Autowired
    private RedisValUtil redisValUtil;

    private int defaultDB = 0;
    // ============================String=============================

    /**
     * 将key-value以String存储进Map
     *
     * @param dbIndex 数据库索引
     * @return Map
     */
    public Map<String, String> value2String(int dbIndex) {
        Set keys = knife4jRedisManager.redisTemplate(dbIndex).keys("*");
        return value2String(dbIndex, keys);
    }

    public Map<String, String> value2String() {
        return value2String(defaultDB);
    }

    /**
     * 将key-value以String存储进Map
     *
     * @param dbIndex 数据库索引
     * @param keys    key数组
     * @return Map
     */
    public Map<String, String> value2String(int dbIndex, Set keys) {
        HashMap<String, String> stringTHashMap = new HashMap<>();
        for (Object key : keys) {
            stringTHashMap.put(key.toString(), redisValUtil.get(dbIndex, key.toString()));
        }
        return stringTHashMap;
    }

    public Map<String, String> value2String(Set keys) {
        return value2String(defaultDB, keys);
    }

    /**
     * 将key-value以String存储进Map
     *
     * @param dbIndex 数据库索引
     * @param key     key值
     * @return Map
     */
    public Map<String, String> value2String(int dbIndex, String key) {
        HashMap<String, String> stringTHashMap = new HashMap<>();
        stringTHashMap.put(key, redisValUtil.get(dbIndex, key));
        return stringTHashMap;
    }

    public Map<String, String> value2String(String key) {
        return value2String(defaultDB, key);
    }

    /**
     * 将key-value(value为Map：Mapkey-JsonStr)以Mapkey和JavaBean存储进Map
     *
     * @param dbIndex 数据库索引
     * @param <T>     JavaBean
     * @return Map
     */
    public <T> Map<String, T> Mapvalue2Bean(int dbIndex, Class<T> clazz) {
        Set keys = knife4jRedisManager.redisTemplate(dbIndex).keys("*");
        return Mapvalue2Bean(dbIndex, clazz, keys);
    }

    public <T> Map<String, T> Mapvalue2Bean(Class<T> clazz) {
        return Mapvalue2Bean(defaultDB, clazz);
    }

    /**
     * 将key-value(value为Map：Mapkey-JsonStr)以Mapkey和JavaBean存储进Map
     *
     * @param dbIndex 数据库索引
     * @param <T>     JavaBean
     * @param keys    key数组
     * @return Map
     */
    public <T> Map<String, T> Mapvalue2Bean(int dbIndex, Class<T> clazz, Set keys) {
        HashMap<String, T> stringTHashMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        for (Object key : keys) {
            Object o = knife4jRedisManager.redisTemplate(dbIndex).opsForValue().get(key.toString());
            if (o != null && o instanceof JSONObject) {
                jsonObject = (JSONObject) o;
                for (String Mapkey : jsonObject.keySet()) {
                    stringTHashMap.put(Mapkey, JSON.parseObject(jsonObject.get(Mapkey).toString(), clazz));
                }
            }
        }
        return stringTHashMap;
    }

    public <T> Map<String, T> Mapvalue2Bean(Class<T> clazz, Set keys) {
        return Mapvalue2Bean(defaultDB, clazz, keys);
    }

    /**
     * 将key-value(value为Map：Mapkey-JsonStr)以Mapkey和JavaBean存储进Map
     *
     * @param dbIndex 数据库索引
     * @param <T>     JavaBean
     * @param key     key值
     * @return Map
     */
    public <T> Map<String, T> Mapvalue2Bean(int dbIndex, Class<T> clazz, String key) {
        HashMap<String, T> stringTHashMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        Object o = knife4jRedisManager.redisTemplate(dbIndex).opsForValue().get(key);
        if (o != null && o instanceof JSONObject) {
            jsonObject = (JSONObject) o;
            for (String Mapkey : jsonObject.keySet()) {
                stringTHashMap.put(Mapkey, JSON.parseObject(jsonObject.get(Mapkey).toString(), clazz));
            }
        }
        return stringTHashMap;
    }

    public <T> Map<String, T> Mapvalue2Bean(Class<T> clazz, String key) {
        return Mapvalue2Bean(defaultDB, clazz, key);
    }

    /**
     * 将key-value(value为JsonStr)以key和JavaBean存储进Map
     *
     * @param dbIndex 数据库索引
     * @param <T>     JavaBean
     * @return Map
     */
    public <T> Map<String, T> Jsonvalue2Bean(int dbIndex, Class<T> clazz) {
        Set keys = knife4jRedisManager.redisTemplate(dbIndex).keys("*");
        return Jsonvalue2Bean(dbIndex, clazz, keys);
    }

    public <T> Map<String, T> Jsonvalue2Bean(Class<T> clazz) {
        return Jsonvalue2Bean(defaultDB, clazz);
    }

    /**
     * 将key-value(value为JsonStr)以key和JavaBean存储进Map
     *
     * @param dbIndex 数据库索引
     * @param <T>     JavaBean
     * @param keys    key数组
     * @return Map
     */
    public <T> Map<String, T> Jsonvalue2Bean(int dbIndex, Class<T> clazz, Set keys) {
        HashMap<String, T> stringTHashMap = new HashMap<>();
        for (Object key : keys) {
            stringTHashMap.put(key.toString(), redisValUtil.get(dbIndex, key.toString(), clazz));
        }
        return stringTHashMap;
    }

    public <T> Map<String, T> Jsonvalue2Bean(Class<T> clazz, Set keys) {
        return Jsonvalue2Bean(defaultDB, clazz, keys);
    }

    /**
     * 将key-value(value为JsonStr)以key和JavaBean存储进Map
     *
     * @param dbIndex 数据库索引
     * @param <T>     JavaBean
     * @param key     key值
     * @return Map
     */
    public <T> Map<String, T> Jsonvalue2Bean(int dbIndex, Class<T> clazz, String key) {
        HashMap<String, T> stringTHashMap = new HashMap<>();
        stringTHashMap.put(key, redisValUtil.get(dbIndex, key, clazz));
        return stringTHashMap;
    }

    public <T> Map<String, T> Jsonvalue2Bean(Class<T> clazz, String key) {
        return Jsonvalue2Bean(defaultDB, clazz, key);
    }
}
