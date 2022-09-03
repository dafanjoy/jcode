package com.jcode.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JedisListApp {
	
    public static void main( String[] args )
    {
    	
        System.out.println( "Hello World!" );   
        for(int i=0;i<1000000;i++) {
        	JedisUtils.LPUSH("test1", i+"");
        }
        long len =  JedisUtils.getJedis().llen("test");
    	System.out.println(len);

        List<String> list = JedisUtils.LRANGE("test",10,len);
        JedisUtils.getJedis().ltrim("test", 0, 9);
       // List<String> list2 = list.subList(0, 10);
        //list.clear();
        for(String val:list) {
        	System.out.println(val);
        }
        
     
    }

}
