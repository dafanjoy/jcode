package com.jcode.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTask implements Runnable {
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	public int i = 0;

//	@Override
//	public void run() {
//		lock.lock(); // 看这里就可以
//		try {
//			for (int j = 0; j < 1000; j++) {
//
//				// lock.lock(); ①
//				i++;
//
//			}
//		} finally {
//			lock.unlock(); // 看这里就可以
//			// lock.unlock();②
//		}
//		System.out.println(i);
//	}
	

	public void run() {
		//if(lock.tryLock()) {
		try {
			if(lock.tryLock(1,TimeUnit.SECONDS)) {
				try {
					for (int j = 0; j < 1000; j++) {
                        Thread.sleep(10);
						// lock.lock(); ①
						i++;

					}
					System.out.println(i);
				} finally {
					lock.unlock(); // 看这里就可以
					// lock.unlock();②
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕"+Thread.currentThread().getName());
	
	}

}
