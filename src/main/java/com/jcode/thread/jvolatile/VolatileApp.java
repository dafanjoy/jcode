package com.jcode.thread.jvolatile;

public class VolatileApp {
	//volatile
	private static boolean isOver = false;

	private static int number = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isOver) {
					//Thread.yield();
				}
				System.out.println(number);
			}
		});
		thread.start();
		Thread.sleep(1000);
		number = 50;
		isOver = true;
	}

}
