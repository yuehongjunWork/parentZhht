package cn.com.common.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

public class RedisClusterUtils {  
	  
    @Autowired  
    private JedisCluster jedisCluster;  
  
    /**  
     * 得到指定key值的value  
     * @param key  
     */  
    public Object get(String key){  
        return jedisCluster.get(key);  
    }  
  
    /**  
     * 保存指定key值的value  
     * @param key  
     * @param value  
     */  
    public void set(String key, String value){  
        jedisCluster.set(key, value);  
    }  
  
    /**  
     * 保存指定key值的value  
     * @param key  
     * @param list  
     */  
    public void set(String key, List<String> list){  
        jedisCluster.rpush(key, (String[]) list.toArray());  
    }  
  
    /**  
     * 删除指定key的value  
     * @param key  
     */  
    public void del(String key){  
        jedisCluster.del(key);  
    }  
}  
