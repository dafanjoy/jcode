package com.jcode.thread.多线程获取结果.CompletableFuture;

import com.jcode.thread.多线程获取结果.Result;

public class WorkThread {

	public static Result call(int num) throws InterruptedException  {
	    Thread.sleep(5*1000);//模拟程序执行时间	
		Result result = new Result();
		result.setValue(String.valueOf(num));
        return result;
	}
}
