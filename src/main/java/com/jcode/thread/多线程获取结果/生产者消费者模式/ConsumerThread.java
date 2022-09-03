package com.jcode.thread.多线程获取结果.生产者消费者模式;

import com.jcode.thread.多线程获取结果.Container;
import com.jcode.thread.多线程获取结果.Result;

public class ConsumerThread extends Thread {
	
	public void run() {
		 while (!this.isInterrupted()) {
			 try {
				Result result = Container.arrayBlockingQueue.take();//有数据就消费，没有就阻塞等待
				System.out.println(result.getValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
}
