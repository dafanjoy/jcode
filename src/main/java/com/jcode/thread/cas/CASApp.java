package com.jcode.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASApp {
	private final static int THREAD_NUM = 1000;
	private final static int MAX_VALUE = 20000000;
	private static AtomicInteger casI = new AtomicInteger(0);
	private static int syncI = 0;
	private String path = "/Users/pingping/DataCenter/Books/Linux/Linux常用命令详解.txt";

	public static void main(String[] args) throws InterruptedException {
		casAdd();

		syncAdd();
	}

	public static void casAdd() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread[] threads = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					
					while (casI.get() < MAX_VALUE) {
						casI.getAndIncrement();
						
					}
					casI.compareAndSet(20000000, 0);
					
				}
			});
			threads[i].start();
		}
		for (int j = 0; j < THREAD_NUM; j++) {
			threads[j].join();
		}
		System.out.println("CAS costs time: " + (System.currentTimeMillis() - begin));
	}

	public static void syncAdd() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread[] threads = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					while (syncI < MAX_VALUE) {
						synchronized ("syncI") {
							++syncI;
						}
					}
				}
			});
			threads[i].start();
		}
		for (int j = 0; j < THREAD_NUM; j++)
			threads[j].join();
		System.out.println("sync costs time: " + (System.currentTimeMillis() - begin));
	}
}
