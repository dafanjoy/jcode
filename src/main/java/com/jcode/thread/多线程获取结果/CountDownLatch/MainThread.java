package com.jcode.thread.多线程获取结果.CountDownLatch;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import com.jcode.thread.多线程获取结果.Result;

public class MainThread {

	public static void main(String[] args) throws InterruptedException {
		Vector<Result> vectors = new Vector<Result>();//定义一个Vector做为存储返回结果的容器；
		final CountDownLatch countDownLatch = new CountDownLatch(5);
		// 启动多个工作线程
		for (int i = 0; i < 5; i++) {
		    WorkThread workThread =	new WorkThread(countDownLatch);
		    workThread.init(vectors);
		    workThread.start();
		}
		System.out.println("主线程等待工作线程执行");
		countDownLatch.await();
		for (int i=0; i<vectors.size(); i++) {
			System.out.println(vectors.get(i).getValue());        
		}
		
	}

}
