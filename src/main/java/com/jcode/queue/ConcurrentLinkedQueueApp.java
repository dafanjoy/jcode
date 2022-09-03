package com.jcode.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueApp {

	public static void main(String[] args) {
		//非阻塞队列
		ConcurrentLinkedQueue<String> quen = new ConcurrentLinkedQueue<String>();
		ConcurrentLinkedQueue<String> quen1 = new ConcurrentLinkedQueue<String>();

		for (int i = 0; i < 100; i++) {
			quen.add(i + "");
		}
		quen1.addAll(quen);// 把quen中的元素全部添加到quen1中
		quen.clear();
		System.out.println(quen.size());
		System.out.println(quen1.size());


	}
}
