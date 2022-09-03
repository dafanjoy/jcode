package com.jcode.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Nthreads=CPU数量
 * Ucpu=目标CPU的使用率，0<=Ucpu<=1
 * W/C=任务等待时间与任务运行时间的比率
 * Nthreads = Ncpu*Ucpu*(1+W/C)
 */

public class ThreadTask implements Runnable{	
	private String taskName;
	
	private ArrayBlockingQueue<Object> arrayAueue = new  ArrayBlockingQueue<Object>(10);
	
	//private volatile int count=0;
	
	public static AtomicInteger acount = new AtomicInteger(0);
	
    ThreadLocal<Integer> seqNum =new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
     };
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public ThreadTask() {

	}
	
	public void init(String s) {
		this.taskName=s;
	}
	
	public void run() {
		try {
			System.out.println(seqNum.hashCode()+"");
			for(int i=0;i<5;i++) {
				seqNum.set(i);
		
				Thread.sleep(10);
				//System.out.println(arrayAueue.take()+"");
				System.out.println(seqNum.get()+"---"+acount+""+Thread.currentThread().getName());
				acount.compareAndSet(0, 1);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//输出执行线程的名称
		//System.out.println("TaskName"+this.getTaskName()+"---ThreadName:"+Thread.currentThread().getName());
	}
}
