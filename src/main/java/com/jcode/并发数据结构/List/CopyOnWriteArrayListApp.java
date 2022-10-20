package com.jcode.并发数据结构.List;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListApp {
	   public static void main(String[] args) throws InterruptedException {
		   
		   ArrayList<Integer> list = new ArrayList<>();
		   CopyOnWriteArrayList cwList = new CopyOnWriteArrayList<>();
		   
		   
		   
		   Thread th1 = new Thread() {
			   @Override
			   public void run() {
				   for(int i=1;i<=50;i++) {
					   cwList.add(i);
				   }
			
			   }
		   };
		   
		   Thread th2 = new Thread() {
			   @Override
			   public void run() {
				   for(int i=51;i<=100;i++) {
					   cwList.add(i);
					  
				   }
				   
				   for(int i=01;i<50;i++) {
					   cwList.remove(i);
				   }
			
			   }
		   };
		   
		   th1.start();
		   th2.start();
		   
           Thread.sleep(1000*10);
		   
  
    	   for(int i=0;i<50;i++) {
			   cwList.get(i);
		   }
		   System.err.println(cwList.size());
	   }
	   
	

}
