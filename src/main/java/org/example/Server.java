package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static int portNumber = 8080; // random port
    static Scanner fetch = new Scanner(System.in);

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer() {

        try {
            System.out.println("waiting for client.. ");
            ServerSocket serversocket = new ServerSocket(portNumber);
            Socket incomingConnection = serversocket.accept();
            System.out.println("connection established");

            PrintWriter outputWriter = new PrintWriter(incomingConnection.getOutputStream(), true);

            // starting a thread so we can recieve and send messages at the same time
            Thread threadForReadingMessages = new Thread(() -> {
                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(incomingConnection.getInputStream()));
                    String messageFromClient;

                    while (true) {
                        messageFromClient = inputReader.readLine();
                        if (messageFromClient.equals("q"))
                            System.exit(0);

                        System.out.println("client: " + messageFromClient);

                    }

                } catch (Exception e) {
                    System.out.println("error");
                }
            });
            threadForReadingMessages.start();

            while (true){
                System.out.println("enter message: ");
                String messageToClient = fetch.nextLine();
                outputWriter.println(messageToClient);

                if (messageToClient.equals("q"))
                    break;
            }



        } catch (Exception e) {
            System.out.println("error");
        }

    }
}


