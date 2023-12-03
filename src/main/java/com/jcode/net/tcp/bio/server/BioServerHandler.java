package com.jcode.net.tcp.bio.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class BioServerHandler implements Runnable{

	private final Socket socket;
	
	public BioServerHandler(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			while (true) {
				byte[] rbytes = new byte[1024];
				InputStream inputStream =  socket.getInputStream(); //通过IO输入流接受消息
	            int rlength=inputStream.read(rbytes, 0, 1024);	//消息长度
				byte[] bytes = new byte[rlength];
				System.arraycopy(rbytes, 0, bytes, 0, rlength);
				String message = new String(bytes);
				System.out.printf("Client: %s%n", message);

				PrintStream writer = new PrintStream(socket.getOutputStream()); //通过IO输出流发送消息
				writer.println("Hello BIO Client");
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
