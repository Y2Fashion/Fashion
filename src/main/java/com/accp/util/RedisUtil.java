package com.accp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("redisUtil")
public class RedisUtil extends RedisConfig{
   /*
   *//**
     * 写入缓存
     * @param key
     * @param value
     * @return
     *//*
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Object, Object> operations = this.redisTemplate().opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/


    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return this.redisTemplate().hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get( String key) {
        Object result = null;
        ValueOperations<Object, Object> operations =this.redisTemplate().opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存并设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Object, Object> operations = this.redisTemplate().opsForValue();
            operations.set(key, value);
            this.redisTemplate().expire(key, 600, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Object> keys = this.redisTemplate().keys(pattern);
        if (keys.size() > 0){
            this.redisTemplate().delete(keys);
        }
    }
    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            this.redisTemplate().delete(key);
        }
    }
   /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        HashOperations<Object, Object, Object> hash = this.redisTemplate().opsForHash();
        hash.put(key,hashKey,value);
        this.redisTemplate().expire(key, 600, TimeUnit.SECONDS);
    }

   /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<Object, Object, Object>  hash = this.redisTemplate().opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        ListOperations<Object, Object> list = this.redisTemplate().opsForList();
        list.rightPush(k,v);
        this.redisTemplate().expire(k, 600, TimeUnit.SECONDS);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        ListOperations<Object, Object> list = this.redisTemplate().opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 获取集合长度
     */
    public Long length(String key){
        ListOperations<Object, Object> list = this.redisTemplate().opsForList();
        return list.size(key);
    }
   /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<Object, Object> set = this.redisTemplate().opsForSet();
        set.add(key,value);
        this.redisTemplate().expire(key, 600, TimeUnit.SECONDS);
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> getMembers(String key){
        SetOperations<Object, Object> set = this.redisTemplate().opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<Object, Object> zset = this.redisTemplate().opsForZSet();
        zset.add(key,value,scoure);
        this.redisTemplate().expire(key, 600, TimeUnit.SECONDS);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<Object, Object> zset = this.redisTemplate().opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}
