package com.jcode.thread.多线程获取结果.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.jcode.thread.多线程获取结果.Result;

public class MainThread {
	 public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
	     ExecutorService taskPool = new ThreadPoolExecutor(5, 15, 1000, TimeUnit.MILLISECONDS,
					new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
	     Future<Result> future = taskPool.submit(new WorkThread());
	     System.out.println("线程池执行工作线程");
    	 Result result = future.get();//注意这里get操作是阻塞，future仍属于同步返回，主线程需要阻塞等待结果返回
    	 //result = future.get(3,TimeUnit.SECONDS);//设置阻塞超时时间
    	 System.out.println(result.getValue());
	 }
}
