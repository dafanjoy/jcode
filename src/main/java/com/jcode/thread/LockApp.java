package com.jcode.thread;

public class LockApp {
	public static void main(String[] args) {
		LockTask test = new LockTask();
		
		Thread t1 = new Thread(test,"t1");
        Thread t2 = new Thread(test,"t2");
        t1.start();
        t2.start();
	}
}
