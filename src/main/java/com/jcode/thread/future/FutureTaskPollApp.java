package com.jcode.thread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskPollApp {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 执行任务 实现Runnable
		FutureTaskJobRunnable taskRun = new FutureTaskJobRunnable();
		// 执行任务 实现Callable
		FutureTaskJobCallable taskCall = new FutureTaskJobCallable();
		String val = "ok";
		// 线程运行成功后把,返回你传入的val值
		FutureTask<String> futureTaskRun = new FutureTask<String>(taskRun, val);
		// 线程运行，返回线程执行的结果
		FutureTask<String> futureTaskCall = new FutureTask<String>(taskCall);
		
		//声明线程池
		ExecutorService executor = Executors.newCachedThreadPool();		
		//Future
//	    Future<String> future =  executor.submit(taskCall);
//	    System.out.println(future.get());
		//FutureTask
		


	    executor.submit(futureTaskCall);
	    Thread.sleep(1000);
		if (futureTaskCall.cancel(true)) {
			System.out.println("线程取消成功");
		}else {
			System.out.println("线程取消失败");
		}
		
		if(futureTaskCall.isCancelled()) {
			System.out.println("线程已经取消");
		}else {
			System.out.println("线程没有取消");
		}

		
		if(futureTaskCall.isDone()) {
			System.out.println("线程已经完成");
		}else {
			System.out.println("线程没有完成");
		}
		//System.out.println(futureTaskCall.get());
//		try {
//			System.out.println(futureTaskCall.get(3,TimeUnit.SECONDS));
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}

		//FutureTask自定义线程执行
		new Thread(futureTaskRun).start();
		System.out.println(futureTaskRun.get());
	
		
		
	
//		System.out.println(futureTaskCall.get());
//
//		executor.execute(taskRun);
//		
//		
//		System.out.print(futureTaskRun.get());
//		System.out.print(futureTaskCall.get());
//		
//		
//		executor.submit(futureTaskCall);
//		// 通过sumbit方法提交,获取Future对象
//		Future<String> res = executor.submit(taskCall);
//
//		res.cancel(false);
//
//		if(res.isDone()) {
//			System.out.println(futureTaskCall.get());	
//		}
		//打印返回值
//		System.out.print(res);
//	       resF.isCancelled();
//	       resF.isDone();
//	       
//	       futureTaskCall.isDone();
//	       futureTaskCall.isCancelled();

//	        try {
//				String a = futureTaskRun.get();
//				System.out.print(a);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
}
