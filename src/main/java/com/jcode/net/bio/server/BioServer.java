package com.jcode.net.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BioServer {
	
	public static void main(String[] args) throws IOException{
		
		ExecutorService executor = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
				new ThreadPoolExecutor.CallerRunsPolicy());
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("127.0.0.1",9091));
		while (true) {
			Socket socket = serverSocket.accept();
			executor.execute(new BioServerHandler(socket));
			
		}
	}
}
