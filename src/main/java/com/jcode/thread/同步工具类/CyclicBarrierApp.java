package com.jcode.thread.同步工具类;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierApp {

	public static void main(String[] args) {

		CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			public void run() {
				System.out.println("所有线程集合");
			}
		});
		for (int i = 0; i < 3; i++) {
			new CyclicBarrierThread(i + "", cb).start();
		}

	}

}
