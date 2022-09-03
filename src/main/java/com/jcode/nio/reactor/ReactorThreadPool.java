package com.jcode.nio.reactor;

public class ReactorThreadPool {
	Selector[] selectors; // Selector集合，每一个Selector 对应一个subReactor线程
	//mainReactor线程
	class Acceptor { // ...
		public synchronized void run() { 
			//...
			Socket connection = serverSocket.accept(); 
			if (connection != null)
			  new Handler(selectors[next], connection); 
			if (++next == selectors.length)
				next = 0;
		}
	}
}
