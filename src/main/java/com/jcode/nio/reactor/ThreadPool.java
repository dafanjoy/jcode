package com.jcode.nio.reactor;

public class ThreadPool {

	class Handler implements Runnable {
		// uses util.concurrent thread pool
		static PooledExecutor pool = new PooledExecutor(...);//声明线程池
		static final int PROCESSING = 3;

		// ...
		synchronized void read() { // ...
			socket.read(input);
			if (inputIsComplete()) {
				state = PROCESSING;
				pool.execute(new Processer());//处理程序放在线程池中执行
			}
		}

		synchronized void processAndHandOff() {
			process();
			state = SENDING; // or rebind attachment
			sk.interest(SelectionKey.OP_WRITE);
		}

		class Processer implements Runnable {
			public void run() {
				processAndHandOff();
			}
		}
	}

}
