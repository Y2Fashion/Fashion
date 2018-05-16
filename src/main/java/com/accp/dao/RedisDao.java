package com.accp.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private RedisTemplate redisTemplate;
    /***
     * 保存到redis中
     * @param key  键
     * @param value  值（字符串类型）
     */
    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
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

    /**
     * 添加一个list
     * @param key
     * @param objectList
     */
    public void addList(String key, List<Object> objectList) {
        for (Object obj : objectList) {
            addList(key, obj);
        }
    }
    /**
     * 向list中增加值
     * @param key
     * @param obj
     * @return 返回在list中的下标
     */
    public long addList(String key, Object obj) {
        //        template.opsForList().rightPush("","");
        //        List<String> range = template.opsForList().range("", 0, 1);
        return redisTemplate.boundListOps(key).rightPush(obj);
    }

    /**
     * 输出完整的list
     * @param key
     */
    public List<Object> getList(String key) {
        return redisTemplate.boundListOps(key).range(0, getListSize(key));
    }

    /**
     * 获取list集合中元素的个数
     * @param key
     * @return
     */
    public long getListSize(String key) {
        return redisTemplate.boundListOps(key).size();
    }

    /**
     * 批量删除key对应的value
     * @param key
     */
    public void remove(String... key) {
        if (key != null && key.length > 0) {
//            if (key.length == 1) {
//                remove(key[0]);
//            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
//            }
        }
    }

    /**
     * 获取Redis中所有的键的key
     * @return
     */
    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }


    /**
     * 判断缓存中是否有key对应的value
     * @param key
     * @return
     */
    public boolean exists(String key) {
        boolean z=redisTemplate.hasKey(key);
        return redisTemplate.hasKey(key);
    }
/*
    boolean exists(final K key);
*/

    /**
     * 判断set中是否存在这个值
     * @param key  对象key
     */
 /*   Boolean hasSetValue(K key,V obj);*/


    /**
     * 向list中增加值
     * @param key
     * @param obj
     * @return 返回在list中的下标
     */
    /*long addList(K key,V ...obj);*/
    /**
     *
     * 输出list
     * @param key List的key
     * @param s 开始下标
     * @param e 结束的下标
     * @return
     */
  /*  List<V> getList(K key, long s, long e);*/
    /**
     * 输出完整的list
     * @param key
     */
  /*  List<V> getList(K key);*/
    /**
     * 获取list集合中元素的个数
     * @param key
     * @return
     */
  /*  long getListSize(K key);*/
    /**
     * 移除list中某值
     * 移除list中 count个value为object的值,并且返回移除的数量,
     * 如果count为0,或者大于list中为value为object数量的总和,
     * 那么移除所有value为object的值,并且返回移除数量
     * @param key
     * @param object
     * @return 返回移除数量
     */
   /* long removeListValue(K key,V object);*/
    /**
     * 移除list中某值
     * @param key
     * @param object
     * @return 返回移除数量
     */
   /* long removeListValue(K key,V... object);*/
    /**
     * 批量删除key对应的value
     * @param keys
     */
  /*  void remove(final K... keys);*/

    /**
     * 删除缓存
     * 根据key精确匹配删除
     * @param key
     */
  /*  void remove(final K key);*/

    /*https://blog.csdn.net/u012957549/article/details/78172938*/
}