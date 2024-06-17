package singlethreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true) {
            try {
                System.out.println("Server is listening on port " + port);
                Socket acceptedconnection = socket.accept();
                System.out.println("connection accepted from the client" + acceptedconnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedconnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedconnection.getInputStream()));
                toClient.println("hello from the server");
                toClient.close();
                fromClient.close();
                acceptedconnection.close();

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {
        server server1 = new server();
        try {
            server1.run();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}