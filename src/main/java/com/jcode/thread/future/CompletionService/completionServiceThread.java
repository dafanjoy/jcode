package com.jcode.thread.future.CompletionService;

import java.util.concurrent.Callable;

public class completionServiceThread implements Callable<String>{
	
    int num;
	
	public completionServiceThread(int num) {
		this.num=num;
	}

	public String call() throws InterruptedException {
		int count = num;
		if(count%2==0) {
			Thread.sleep(num*1000);
		}
		for(int i=0;i<=1000;i++) {
			count++;
		}
		
		
		return "线程"+num+"返回值："+count;
	}
}
