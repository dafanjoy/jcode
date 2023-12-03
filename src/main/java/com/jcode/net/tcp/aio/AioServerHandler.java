package com.jcode.net.tcp.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

public class AioServerHandler implements Runnable {

    private AsynchronousSocketChannel clientChannel;
    public  AioServerHandler(AsynchronousSocketChannel clientChannel){
        this.clientChannel=clientChannel;
    }
    @Override
    public void run() {
        ByteBuffer inputBuff = ByteBuffer.allocate(1024); // 分配读ByteBuffer
        ByteBuffer outputBuff = ByteBuffer.allocate(1024); // 分配写ByteBuffer
        clientChannel.read(inputBuff, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer bytesRead, Void attachment) {
                if (bytesRead == -1) {
                    // 客户端关闭连接
                    try {
                        clientChannel.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }

                inputBuff.flip();
                byte[] data = new byte[bytesRead];
                inputBuff.get(data);
                String message = new String(data);
                System.out.println("Received message: " + message);


                outputBuff.clear();

                outputBuff.put(message.getBytes(StandardCharsets.UTF_8));
                outputBuff.flip();
                clientChannel.write(outputBuff, null, new CompletionHandler<Integer, Void>() {
                    @Override
                    public void completed(Integer result, Void attachment) {
                        System.out.println("Sent response: " + message);
                    }

                    @Override
                    public void failed(Throwable exc, Void attachment) {
                        exc.printStackTrace();
                    }
                });

                inputBuff.clear();
                clientChannel.read(inputBuff, null, this);
            }

            @Override
            public void failed(Throwable exce, Void attachment) {
                exce.printStackTrace();
                try {
                    clientChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
