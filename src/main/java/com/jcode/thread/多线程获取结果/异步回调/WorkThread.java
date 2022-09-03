package com.jcode.thread.多线程获取结果.异步回调;

import java.util.concurrent.Callable;

import com.jcode.thread.多线程获取结果.Result;

public class WorkThread implements Runnable{
    int num;//线程编号
    
    CallBack callBack;
    
	public WorkThread(CallBack callBack, int num) {
		this.num=num;
		this.callBack = callBack;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	    try {
			Thread.sleep((10-num)*1000);//模拟程序运行时间，倒序输出
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Result result = new Result();
		result.setValue(num+"号线程执行完毕，输出结果");
		callBack.notice(result);
	}
}
