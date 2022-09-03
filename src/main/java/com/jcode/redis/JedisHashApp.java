package com.jcode.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisHashApp {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );   
        Map<String, String> map = new HashMap<String, String>();
        for(int i=0;i<4;i++) {
            map.put(i+"", "123"+i);
        }
        //存储map
        JedisUtils.HSET("testMap",map);
        
        Map<String, String> mapTest  =  JedisUtils.HGETALL("testMap");
        
        for (Map.Entry<String, String> entry : mapTest.entrySet()) {
           System.out.println(entry.getValue());
        }
        
        
     
    }
}
