package com.jcode.queue.disruptor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
	
	public static int infoNum = 50000000;
    static long startTime = System.currentTimeMillis();
	public static void main(String[] args) {
		final BlockingQueue<InfoEvent> queue = new LinkedBlockingQueue<InfoEvent>(100);
		
		new Thread(new Runnable() {
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
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				int cnt = 0;
				while (true) {
					try {
						queue.take();
					} catch (InterruptedException e) {
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
