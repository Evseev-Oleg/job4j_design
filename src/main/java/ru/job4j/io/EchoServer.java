package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String http = "HTTP/1.1 200 OK\r\n\r\n";
                    if (!str.isEmpty()) {
                        String[] massStr = str.split(" ");
                        massStr = massStr[1].split("=");
                        if (massStr.length > 1) {
                            if (massStr[1].equals("Exit")) {
                                out.write(http.getBytes());
                                return;
                            } else if (massStr[1].equals("Hello")) {
                                out.write(http.getBytes());
                                out.write("Hello".getBytes());
                            } else {
                                out.write(http.getBytes());
                                out.write("What".getBytes());
                            }
                        }
                        System.out.println(str);
                    }
                }
            }
        }
    }
}
