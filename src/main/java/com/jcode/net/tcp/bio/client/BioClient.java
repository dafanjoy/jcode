package com.jcode.net.tcp.bio.client;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BioClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8091));

        while (true) {
            if (!socket.isConnected()) {
                System.out.print("connecting...");
                continue;
            }

            PrintStream writer = new PrintStream(socket.getOutputStream());
            writer.write("Hello BIO Server".getBytes(StandardCharsets.UTF_8));

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.printf("Server: %s%n", message);
        }


    }
}
