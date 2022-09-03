package com.jcode.thread.future;

import java.util.concurrent.Callable;

public class FutureTaskJobCallable implements Callable<String>{
    
	public String call() throws Exception {
		int i=0;
		System.out.println("FutureTaskJobCallable已经执行了哦");
		Thread.sleep(5000);
//		return "返回结果";
        while (i<100000&&!Thread.currentThread().isInterrupted()/**/) {
            //System.out.println("i++:"+i);
            i++;
        }
        System.out.println(i);
        return i+"";
	}

}
