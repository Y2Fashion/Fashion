package com.accp.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;

    /***
     * 保存到redis中
     * @param key  键
     * @param value  值（字符串类型）
     */
    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,60, TimeUnit.MINUTES);//1小时过期
    }

    /***
     * 从redis中取值
     * @param key 键
     * @return 值（字符串类型）
     */
    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

}