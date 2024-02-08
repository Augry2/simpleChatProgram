package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static String serverAddress = "localhost"; // Assuming the server is running on the same machine
    static int serverPort = 8080;
    static Scanner fetch = new Scanner(System.in);

    public static void main(String[] args) {
        connectToTheServer();
    }

    public static void connectToTheServer(){

        try{
            System.out.println("connecting.. ");
            Socket incomingConnection = new Socket(serverAddress, serverPort);
            System.out.println("connected");

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(incomingConnection.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(incomingConnection.getOutputStream(), true);

            Thread threadForReadingMessages = new Thread(() -> {
                try {
                    while (true){
                        String messageFromServer = inputReader.readLine();
                        if (messageFromServer.equals("q"))
                            System.exit(0);

                        System.out.println("server: " + messageFromServer);



                    }
                }catch (Exception e){
                    System.out.println("error");
                }
            });
            threadForReadingMessages.start();

            boolean chat = true;
            while (chat) {
                String messageToServer = fetch.nextLine();
                outputWriter.println(messageToServer);

                if (messageToServer.equals("q"))
                    chat = false;
            }
        }catch (Exception e){
            System.out.println("error");
        }
    }
}
