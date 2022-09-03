package com.jcode.thread.同步工具类;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThread extends Thread{
	
	private CyclicBarrier barrier;
	
	private String name;
	
	private int count;
	
	public CyclicBarrierThread(String name,CyclicBarrier barrier) {
		this.name=name;
		this.barrier=barrier;
		this.count=0;
	}
	
	public void run() {
		try {
			for(int i=0;i<10;i++) {
				
				Thread.sleep(100);
				count++;
				System.out.println(name+"号线程---"+Thread.currentThread().getName()+"开始计数:"+count);
				if(count%2==0) {//每计数到偶数次时集合一次
					System.out.println(barrier.await());
					System.out.println(name+"号线程---"+Thread.currentThread().getName()+"集合完毕，继续计数");
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
