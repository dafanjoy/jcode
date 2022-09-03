package com.jcode.thread.多线程获取结果.CompletionService;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jcode.thread.future.CompletionService.completionServiceThread;
import com.jcode.thread.多线程获取结果.Result;

public class MainThread {
public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService exec = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		//定义一个阻塞队列
		BlockingQueue<Future<Result>> futureQueue =	new LinkedBlockingQueue<Future<Result>>();
		
		//传入ExecutorService与阻塞队列，构造一个completionService
	    CompletionService<Result> completionService = new ExecutorCompletionService<Result>(
				exec,futureQueue);
	    
	    for(int i=0;i<10;i++) {
	    	completionService.submit(new WorkThread(i));
	    }
	    
	    for(int i=0;i<10;i++) {
	    	Result res = completionService.take().get();//注意阻塞队列take操作，如果获取不到数据时处于阻塞状态的
	    	System.out.println(new Date()+ "--"+res.getValue());
	    }
	}
}
