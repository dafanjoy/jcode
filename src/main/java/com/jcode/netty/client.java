package com.jcode.netty;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class client {	
	
	public static void main(String[] args) {
		doConnect();
	}
	
	
    public static void doConnect() {

        Bootstrap bootstrap = new Bootstrap();


        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();


        try {
            if (bootstrap != null) {

                bootstrap.group(nioEventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast("idleStateHandler",
                                new IdleStateHandler(0, 3, 0, TimeUnit.MINUTES));

                    }
                });
                bootstrap.remoteAddress("127.0.0.1", 8092);
                ChannelFuture f = bootstrap.connect();
//               ChannelFuture f = bootstrap.connect().addListener((ChannelFuture futureListener) -> {
//      
//                    if (!futureListener.isSuccess()) {
//                        //futureListener.channel().eventLoop().schedule(() -> doConnect(), 10L, TimeUnit.SECONDS);
//                    } else {
//                        //logger.info("ClientId:" + config.clientId() + " monitoring service has started,ready to connect server");
//                    }
//                });
               f.channel().closeFuture().sync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nioEventLoopGroup.shutdownGracefully();
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            doConnect(); 
        }
    }

}
