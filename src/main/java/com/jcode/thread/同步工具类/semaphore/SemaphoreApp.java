package com.jcode.thread.同步工具类.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreApp {

	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(2);

		// 启动计数线程
		for (int i = 1; i <= 10; i++) {
			new SemaphoreThread(semaphore).start();
		}
	}
}
