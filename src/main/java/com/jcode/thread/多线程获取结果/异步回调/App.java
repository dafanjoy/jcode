package com.jcode.thread.多线程获取结果.异步回调;

import org.apache.kafka.clients.admin.NewPartitions;

public class App {
	 public static void main(String[] args) {
		 MainThread mainThread = new MainThread();
		 for(int i=0;i<10;i++) {
			 mainThread.run(i);
		 }
		 System.out.println("继续执行，表示异步操作");
	 }
}
