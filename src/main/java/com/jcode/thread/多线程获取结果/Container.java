package com.jcode.thread.多线程获取结果;

import java.util.concurrent.ArrayBlockingQueue;

public class Container {
	public static ArrayBlockingQueue<Result> arrayBlockingQueue = new ArrayBlockingQueue<>(100);//这里最好根据系统负载量评估一个阈值，避免OOM问题
}
