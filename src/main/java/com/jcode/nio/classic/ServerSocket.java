package com.jcode.nio.classic;

import java.io.IOException;
import java.net.Socket;

public class ServerSocket {
	class Server implements Runnable {
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(PORT);
				while (!Thread.interrupted())
					new Thread(new Handler(ss.accept())).start();
				// or, single-threaded, or a thread pool
			} catch (IOException ex) {
				/* ... */ }
		}

		static class Handler implements Runnable {
			final Socket socket;

			Handler(Socket s) {
				socket = s;
			}

			public void run() {
				try {
					byte[] input = new byte[MAX_INPUT];
					socket.getInputStream().read(input);
					byte[] output = process(input);
					socket.getOutputStream().write(output);
				} catch (IOException ex) {
					/* ... */ }
			}

			private byte[] process(byte[] cmd) {
				/* ... */ }
		}
	}

	public Socket accept() {
		// TODO Auto-generated method stub
		return null;
	}

}
