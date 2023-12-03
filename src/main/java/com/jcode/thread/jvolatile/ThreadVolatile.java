package com.jcode.thread.jvolatile;

public class ThreadVolatile  implements Runnable {
	
	private boolean shutdownRequested=false;
	
	public void run() {
		while(!shutdownRequested) {
			System.out.println("shutdownRequested:"+shutdownRequested);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() { 
	    shutdownRequested = true; 
	}
}
