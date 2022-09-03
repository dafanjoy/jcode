package com.jcode.thread;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class NettyServer {
	
    private static final int BIZ_GROUP_SIZE = 1;
    private static final int BIZ_THREAD_SIZE = 8;
	private volatile boolean isRunning = false;//服务器运行状态
    
    //连接线程
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZ_GROUP_SIZE);
    //工作线程
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZ_THREAD_SIZE);

}
