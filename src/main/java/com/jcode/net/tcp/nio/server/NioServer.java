package com.jcode.net.tcp.nio.server;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NioServer {
	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
	        // 1.获取Selector选择器
	        Selector selector = Selector.open();
	        // 2.获取通道
	        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
	        // 3.设置为非阻塞
	        serverSocketChannel.configureBlocking(false);
	        // 4.绑定连接
	        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8091));
	        // 5.将通道注册到选择器上,并注册的操作为：“接收”操作
	        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	        
	        
	        // 6.采用轮询的方式，查询获取“准备就绪”的注册过的操作
	        while (true) {
	            selector.select();
	            Set<SelectionKey> selectionKeys = selector.selectedKeys();
	            Iterator<SelectionKey> iterator = selectionKeys.iterator();
	            while (iterator.hasNext()) {
	                SelectionKey selectionKey = iterator.next();
					new NioServerHandler(serverSocketChannel, selectionKey).handle();
	            }
	           selectionKeys.clear();
	        }
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

}
