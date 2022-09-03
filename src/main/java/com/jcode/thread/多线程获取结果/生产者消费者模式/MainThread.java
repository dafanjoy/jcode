package com.jcode.thread.多线程获取结果.生产者消费者模式;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThread  {
	public static void main(String[] args) {
		
		ExecutorService exec = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		for(int i=0;i<100;i++) {//使用线程池模拟生成者生产数据
			exec.execute(new ProducerThread());
		}
		
		for(int i=0;i<2;i++) {//启动两个消费者线程
			new ConsumerThread().start();
		}
	}
}
