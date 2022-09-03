package com.jcode.disruptor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlcokingQueueTest {
	public static int infoNum = 500000;
	static long startTime = System.currentTimeMillis();
	public static void main(String[] args) {
		final BlockingQueue<InfoEvent> queue = new ArrayBlockingQueue<InfoEvent>(100);
		
	    ExecutorService pool = Executors.newFixedThreadPool(10);
		
		Thread t1 =  new Thread(new Runnable() {

			@Override
			public void run() {
				int pcnt = 0;
				while (true) {
					InfoEvent kafkaInfoEvent = new InfoEvent(pcnt, pcnt + "info");
					try {
						queue.put(kafkaInfoEvent);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pcnt++;
				}
			}
		});
		
		for(int i=0;i<10;i++) {
			pool.execute(t1);
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				int cnt = 0;
				while (true) {
					try {
						queue.take();
					} catch (Exception e) {
						e.printStackTrace();
					}
					cnt++;
					if(cnt>50000000) {
						cnt =0;
						long endTime = System.currentTimeMillis();
						System.out.println("消耗时间 ： " + (endTime - startTime) + "毫秒");
						startTime = System.currentTimeMillis();
						
					}
			
				}
				
	
			}
		}).start();
	}
}
