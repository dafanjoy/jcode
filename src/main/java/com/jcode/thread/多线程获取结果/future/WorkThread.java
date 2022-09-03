package com.jcode.thread.多线程获取结果.future;

import java.util.concurrent.Callable;

import com.jcode.thread.多线程获取结果.Result;

public class WorkThread implements Callable<Result> {
	
	public Result call() throws Exception {
		Thread.sleep(5000);
		Result result = new Result();
		result.setValue(Thread.currentThread().getName()+"线程执行完毕，输出结果");
        return result;
	}
}
