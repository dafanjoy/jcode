package com.jcode.thread.future;

public class FutureTaskJobRunnable implements Runnable {
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FutureTaskJobRunnable已经执行了哦");
	}

}
