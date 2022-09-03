package com.jcode.thread.实现一个同步锁;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.common.requests.AlterReplicaLogDirsRequest;

import com.lmax.disruptor.YieldingWaitStrategy;

public class syncLock {
	private volatile AtomicInteger status = new AtomicInteger(0);
	private ArrayBlockingQueue<Thread> queue =  new ArrayBlockingQueue<Thread>(10);
	void lock() {
		while(!status.compareAndSet(0,1)) {
			Thread.yield();
		}
	}
	
	private void voi() {
		// TODO Auto-generated method stub

	}

}
