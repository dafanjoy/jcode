package com.jcode.thread.同步工具类.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchThread extends Thread {

	private CountDownLatch countDownLatch;

	private int name;

	private int count;

	public CountDownLatchThread(int name, CountDownLatch countDownLatch) {
		this.name = name;
		this.countDownLatch = countDownLatch;
		this.count = 0;
	}

	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(100);
				count++;
			}
			System.out.println(name + "号线程--" + Thread.currentThread().getName() + "--计数完成了");
			countDownLatch.countDown();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
