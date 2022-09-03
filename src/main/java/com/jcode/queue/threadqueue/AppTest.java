package com.jcode.queue.threadqueue;

import java.util.Date;

public class AppTest {
	public static void main(String[] args) {
		SealSynArrayBlockQueue.initQueue(100);
		Thread thread =  new Thread() {
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()){
					SealSynArrayBlockQueue.put(new Date().getTime());
				}
			}
			
		};
		
		thread.start();
		
		Thread thread2 =  new Thread() {
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()){
					try {
						System.out.println(SealSynArrayBlockQueue.take().toString());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					}
			}
			
			
		};
		
		thread2.start();
		
	}
}
