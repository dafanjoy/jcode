package com.jcode.thread.cas;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;



public class CasUpateApp {
	
	private static  AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    
	public static void main(String[] args) throws InterruptedException {
		if (atomicBoolean.get()==false) {
			if (atomicBoolean.compareAndSet(true, false)) {//通过CAS的方式保证线程
				try {
					System.err.println("11111");
				} catch (Throwable cause) {
				
				}
			}
		}
		System.err.println("222222");
		
	}

}
