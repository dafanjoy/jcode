package com.jcode.thread.多线程获取结果.threadjoin;

import com.jcode.thread.多线程获取结果.Result;

public class WorkThread extends Thread {
	
	private Result result ;
	
	public void init(Result result) {
		this.result = result;
	}
	
	public void run() {
		try {
			Thread.sleep(1000*10);//模拟程序执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setValue("线程执行完毕，输出结果");
    }

}
