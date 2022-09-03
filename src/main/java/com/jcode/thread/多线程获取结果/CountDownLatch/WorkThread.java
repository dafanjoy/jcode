package com.jcode.thread.多线程获取结果.CountDownLatch;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import com.jcode.thread.多线程获取结果.Result;

public class WorkThread extends Thread {
	private Vector<Result> vectors ;
	
	 private CountDownLatch countDownLatch;
	
	public WorkThread(CountDownLatch countDownLatch) {
		this.countDownLatch=countDownLatch;
	}
	
	public void init(Vector<Result> vectors) {
		this.vectors = vectors;
	}
	
	public void run() {
		try {
			Thread.sleep(1000*3);//模拟程序执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Result result = new Result();
		result.setValue(Thread.currentThread().getName()+"线程执行完毕，输出结果");
		vectors.add(result);//结果放入Vector中
		countDownLatch.countDown();
    }
}
