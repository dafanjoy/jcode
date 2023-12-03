package com.jcode.net.tcp.aio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AioServer {

    static ExecutorService executor = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
            new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();

        serverChannel.bind(new InetSocketAddress("127.0.0.1", 8091));

            serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                @Override
                public void completed(AsynchronousSocketChannel clientChannel, Void attachment) {
                    // 继续接受下一个连接
                    serverChannel.accept(null, this);

                    executor.execute(new AioServerHandler(clientChannel));
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                }
            });

            Thread.currentThread().join();
        }

}
