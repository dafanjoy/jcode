package com.jcode.net.nio.server;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.google.common.io.ByteProcessor;
import com.jcode.utils.BytesUtils;

public class NioServerHandler implements Runnable {
	
	private ServerSocketChannel serverSocketChannel;
	
	private SelectionKey selectionKey;
	
	public  NioServerHandler(ServerSocketChannel serverSocketChannel, SelectionKey selectionKey) {
		this.serverSocketChannel=serverSocketChannel;
		this.selectionKey=selectionKey;
	}

    public void run() {
        ByteBuffer inputBuff = ByteBuffer.allocate(1024); // 分配读ByteBuffer
        ByteBuffer outputBuff = ByteBuffer.allocate(1024); // 分配写ByteBuffer
        try {
            if (selectionKey.isAcceptable()) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    return;
                }
                socketChannel.configureBlocking(false);
                socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
            }

            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                if (socketChannel == null) {
                    return;
                }

                inputBuff.clear();
                int length = socketChannel.read(inputBuff);
                if (length == -1) {
                    socketChannel.close();
                    selectionKey.cancel();
                    return;
                }

                inputBuff.flip();
             
				byte[] bytes = new byte[length];
				System.arraycopy(inputBuff.array(), 0, bytes, 0, length);
                
                System.err.println( BytesUtils.toHexString(bytes));
                socketChannel.register(selectionKey.selector(), SelectionKey.OP_WRITE);
                selectionKey.selector().wakeup();
            }

            if (selectionKey.isWritable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                if (socketChannel == null) {
                    return;
                }
                String message = "Hello, Client. " + UUID.randomUUID();
                outputBuff.put(message.getBytes(StandardCharsets.UTF_8));
                outputBuff.flip();
                socketChannel.write(outputBuff);

                socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                selectionKey.selector().wakeup();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
