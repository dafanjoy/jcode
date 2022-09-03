package com.jcode.thread.多线程获取结果.threadjoin;

import com.jcode.thread.多线程获取结果.Result;

public class MainThread {
    public static void main(String[] args) throws InterruptedException {
    	Result result = new Result();
    	WorkThread workThread = new WorkThread();
    	workThread.init(result);
    	System.out.println("线程启动");
    	workThread.start();
    	System.out.println("线程等待");
        // 等待work线程运行完再继续运行
    	workThread.join(5000);
    	workThread.interrupt();
    	System.out.println("线程执行结果："+result.getValue());
    }
}
