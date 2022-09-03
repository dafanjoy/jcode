package com.jcode.thread.同步工具类.CountDownLatch;

import java.util.concurrent.CountDownLatch;

import com.jcode.thread.同步工具类.CyclicBarrierThread;

public class CountDownLatchApp {
	public static void main(String[] args) {
		final CountDownLatch countDownLatch = new CountDownLatch(5);

		// 启动计数线程
		for (int i = 0; i < 5; i++) {
			new CountDownLatchThread(i, countDownLatch).start();
		}

		// 启动等待线程
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {

					try {
						countDownLatch.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("计数完毕了," + Thread.currentThread().getName() + "等待线程执行");

				}
			}.start();
		}

	}
}
