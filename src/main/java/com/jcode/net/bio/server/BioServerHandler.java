package com.jcode.net.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;

import com.jcode.utils.BytesUtils;

public class BioServerHandler implements Runnable{

	private Socket socket;
	
	public BioServerHandler(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			while (true) {
				ByteBuffer buffer =  ByteBuffer.allocate(1024);
				byte[] rbytes = new byte[1024];
				InputStream inputStream =  socket.getInputStream();
			
				//消息长度
	            int rlength=inputStream.read(rbytes, 0, 1024);
				byte[] bytes = new byte[rlength];
				System.arraycopy(rbytes, 0, bytes, 0, rlength);
		         
                
				//inputStream.read(bytes);
				
				//BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

				//ByteBuffer buffer =  ByteBuffer.allocate(1024);
//				
				String message = BytesUtils.toHexString(bytes);
	            System.err.println("receive:"+message);
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
