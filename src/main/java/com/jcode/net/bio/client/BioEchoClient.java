package com.jcode.net.bio.client;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BioEchoClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8099));

        boolean flag = true;

        while (flag) {
            if (!socket.isConnected()) {
                log.info("connecting...");
                continue;
            }

            PrintStream writer = new PrintStream(socket.getOutputStream());
            writer.println("Hello BIO Server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            log.info("[SERVER]: {}", message);

            writer.println("bye");

            if (message.equalsIgnoreCase("EXIT")) {
                writer.close();
                reader.close();
                socket.close();
                flag = false;
            }
        }


    }
}
