package com.jcode.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerApp {
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Map<String,String> cmap = new ConcurrentHashMap<String,String>();
        cmap.put("1", "1");
 
        
    }
}
