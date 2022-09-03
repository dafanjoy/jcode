package com.jcode.thread.多线程获取结果.异步回调;

import com.jcode.thread.多线程获取结果.Result;

public class MainThread implements CallBack {
	
	public void run(int num) {
         WorkThread workThread =  new WorkThread(this,num);
         new Thread(workThread).start();
	}

	@Override
	public void notice(Result result) {
		System.out.println("返回结果："+result.getValue());	
	}

}
