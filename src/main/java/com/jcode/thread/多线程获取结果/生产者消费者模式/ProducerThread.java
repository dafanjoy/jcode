package com.jcode.thread.多线程获取结果.生产者消费者模式;

import java.util.concurrent.TimeUnit;

import com.jcode.thread.多线程获取结果.Container;
import com.jcode.thread.多线程获取结果.Result;

public class ProducerThread extends Thread {
	
	public void run() {	
		try {
			Thread.sleep(1000*3);//模拟程序执行
			Result result = new Result();
			result.setValue(Thread.currentThread().getName()+"线程执行完毕，输出结果");
			Container.arrayBlockingQueue.put(result);//超过阻塞队列最大阈值时阻塞，一直阻塞
//			if(!Container.arrayBlockingQueue.offer(result, 5, TimeUnit.SECONDS)) {//规定时间内数据入队失败
//				System.err.println("数据入队失败");
//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
