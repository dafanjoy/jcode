package com.jcode.thread.future.CompletionService;

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

public class CompletionServiceApp {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService exec = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		BlockingQueue<Future<String>> futureQueue =	new LinkedBlockingQueue<Future<String>>();
		
	    CompletionService<String> completionService = new ExecutorCompletionService<String>(
				exec,futureQueue);
	    
	    for(int i=0;i<10;i++) {
	    	completionService.submit(new completionServiceThread(i));
	    }
	    
	    for(int i=0;i<10;i++) {
	    	String res = completionService.take().get();
	    	System.out.println(res);
	    }
	}
}
