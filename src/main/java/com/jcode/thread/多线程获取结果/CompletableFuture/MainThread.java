package com.jcode.thread.多线程获取结果.CompletableFuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jcode.thread.多线程获取结果.Result;

public class MainThread {
	
	 public static void main(String[] args) {
	        List<String> reslist = new ArrayList<String>();
	        ExecutorService exs = Executors.newFixedThreadPool(10);//定义一个线程池
	        List<CompletableFuture<Result>> futureList = new ArrayList<>();
	        try {
	        	for(int i=0;i<10;i++) {
	        	    final int k = i;
	        	    CompletableFuture<Result> future=CompletableFuture.supplyAsync(()->{
						try {
							return WorkThread.call(k);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return null;
					},exs).thenApply(e->mul(e)).whenComplete((v, e) -> {//thenApply 里面执行就是回调函数CallBack
	    	                        System.out.println("线程"+k+"完成! 结果："+v.getValue()+"，异常 ："+e+","+new Date());
	    	                        reslist.add(v.getValue());
	    	                    });
	        	    
	        	    futureList.add(future);//聚合返回结果
	        	}
	        	System.out.println("直接输出，标识异步操作");


	        }catch (Exception e) {
				// TODO: handle exception
			}
	    }
	    
	    
	    public static Result mul(Result result){
	        try {
	        	Integer val = Integer.valueOf(result.getValue())*2;
	        	result.setValue(val.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }



}
