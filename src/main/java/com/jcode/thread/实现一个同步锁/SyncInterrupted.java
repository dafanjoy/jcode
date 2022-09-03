package com.jcode.thread.实现一个同步锁;

import java.util.concurrent.locks.ReentrantLock;

public class SyncInterrupted {

	private static ReentrantLock reentrantLock = new ReentrantLock();



	public static void main(String[] args) {
		
		
	    Thread t1 = new Thread() {
			public void run() {
				try {
					test();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			
		};
		
		t1.setName("t1");
		
		Thread t2 = new Thread() {
			public void run() {
				try {
					test();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			
		};
		

		t2.setName("t2");
		
		t1.start();
		
		t2.start();
		
		t2.interrupt();
		
		System.out.println("main");
	}

	static void test() throws InterruptedException {

		// TODO Auto-generated method stub
		//System.out.println(Thread.currentThread().getName());
		if (Thread.interrupted()) {
			System.err.println(Thread.currentThread().getName()+ "  Thread.interrupted()");
		}
		reentrantLock.lock();
		
		System.out.println(Thread.currentThread().getName()+"  获取到锁");
		
		try {
         Thread.sleep(9000000);
		} finally {
			reentrantLock.unlock();
		}
	}

}
