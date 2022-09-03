package com.jcode.thread.多线程获取结果.CompletionService;

import java.util.concurrent.Callable;

import com.jcode.thread.多线程获取结果.Result;

public class WorkThread implements Callable<Result>{
    int num;//线程编号
    
	public WorkThread(int num) {
		this.num=num;
	}
	
	public Result call() throws InterruptedException {
		int count = num;
		if(count%2==0) {//编号为偶数的线程阻塞3秒钟
			Thread.sleep(3*1000);
		}
		Result result = new Result();
		result.setValue(num+"号线程执行完毕，输出结果");
        return result;
	}
}
