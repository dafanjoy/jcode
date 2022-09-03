package com.jcode.redis;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    
    private static String ADDR_ARRAY = "127.0.0.1";

    //Redis的端口号
    //private static int PORT = 6379;
    private static int PORT = 6380;
    //访问密码
    private static String AUTH = "";
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 500;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 100;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10 * 1000;

    private static int TIMEOUT = 10 * 1000;//超时时间

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);

            config.setTestOnBorrow(TEST_ON_BORROW);//使用时进行扫描，确保都可用

            config.setTestWhileIdle(true);//Idle时进行连接扫描

            config.setTestOnReturn(true);//还回线程池时进行扫描
//
////表示idle object evitor两次扫描之间要sleep的毫秒数
//            config.setTimeBetweenEvictionRunsMillis(30000);
//
////表示idle object evitor每次扫描的最多的对象数
//            config.setNumTestsPerEvictionRun(10);
//
////表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
//            config.setMinEvictableIdleTimeMillis(60000);

            if (!AUTH.equals("")) {
                jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[0], PORT, TIMEOUT, AUTH);
            } else {
                jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[0], PORT, TIMEOUT);
            }

        } catch (Exception e) {
            try {
                //如果第一个IP异常，则访问第二个IP
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(MAX_ACTIVE);
                config.setMaxIdle(MAX_IDLE);
                config.setMaxWaitMillis(MAX_WAIT);
                config.setTestOnBorrow(TEST_ON_BORROW);
                jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[1], PORT, TIMEOUT, AUTH);
            } catch (Exception e2) {

            }
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }


    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }

        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
                jedis.select(3);
            }
        } catch (Exception e) {

        } finally {
            returnResource(jedis);//归还到Redis池里面
        }
        return jedis;
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
        	jedis.close();
            //jedisPool.returnResource(jedis);
        	
        }
    }

    /**
     * 关闭连接池
     */
    public static void closePool() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized static void setString(String key, String value) {
        try {
            getJedis().set(key, value);
        } catch (Exception e) {
        }
    }

    /**
     * 设置 过期时间
     *
     * @param key
     * @param seconds 以秒为单位
     * @param value
     */
    public synchronized static void setString(String key, int seconds, String value) {
        try {
            getJedis().setex(key, seconds, value);
        } catch (Exception e) {

        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized static String getString(String key) {
        if (getJedis() == null || !getJedis().exists(key)) {
            return null;
        }
        return getJedis().get(key);
    }
    
    /**
     * LPUSH key value [value ...]  将一个或多个值 value 插入到列表 key 的表头
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
     * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * <p/>
     * LPUSHX key value
     * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
     * 和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
     * <p/>
     * RPUSH key value [value ...] 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * <p/>
     * RPUSHX key value
     * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
     * 和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做。
     */
	public static void LPUSH(String key, String value) {
        try {
            getJedis().lpush(key, value);
        } catch (Exception e) {
        }
    }
    
    /**
     * LRANGE key start stop
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     */

    public synchronized static List<String>  LRANGE(String key,long startIndex,long endIndex) {
        return getJedis().lrange(key, startIndex, endIndex);
    }
    
    
    /**
     * 
     * @param KEY
     * @param map
     */
    public static void HSET(String KEY,Map<String,String> map) {
        //jedis.hset(KEY, FIELD, VALUE);

        getJedis().hmset(KEY, map);
        
        
       
  
    }
    
    
    /**
     * 
     * @param KEY
     * @return
     */
    public static Map<String,String> HGETALL(String KEY) {
        Map<String, String> map = getJedis().hgetAll(KEY);
        return map;
    }
    
    


}
