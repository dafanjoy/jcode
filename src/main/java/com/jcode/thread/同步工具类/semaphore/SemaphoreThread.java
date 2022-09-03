package com.jcode.thread.同步工具类.semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SemaphoreThread extends Thread {

	private Semaphore semaphore;

	public SemaphoreThread(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public void run() {
		try {
			semaphore.acquire();//获取执行许可
			Thread.sleep(2000);
			System.out.println(this.getName() + "线程，" + "开始进行计数");
			// 模拟计数时长
			Thread.sleep(2000);
			// 一个线程完成，允许下一个线程开始计数
			System.out.println(this.getName() + "线程，" + "计数完毕");
			semaphore.release();//归还许可

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
