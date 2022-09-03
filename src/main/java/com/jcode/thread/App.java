package com.jcode.thread;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ReentrantReadWriteLock r = new ReentrantReadWriteLock ();
        System.out.println( "Hello World!" );
        
        HashMap<String,String> map = new HashMap();
        
        int k = tableSizeFor(228);
        System.err.println(k);
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("a");
        
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        
        removeList(list);
        
        HashMap<Integer,String> map1 = new HashMap<Integer,String>();
        for(int i = 0; i < 10; i++){  
        	map1.put(i, "value" + i);  
        }


    }
    
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }
    
    static void removeList(ArrayList<String> list) {
    	for(int i=0;i<list.size();i++) {
    		if(list.get(i).contains("c")) {
    			list.remove(i);
    		}
    	}
    	
    	for(int i=0;i<list.size();i++) {
    		  System.out.println(list.get(i));
    	}
    }
    
    static void removeMap(HashMap map) {
    	Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
    	while(it.hasNext()){            
    		Map.Entry<Integer, String> entry = it.next();            
    		Integer key = entry.getKey();            
    		if(key % 2 == 0){           	 
    			System.out.println("To delete key " + key);           	
    			it.remove();               	
    			System.out.println("The key " + + key + " was deleted");            
    			}       
    		}
 
    }
   
}
