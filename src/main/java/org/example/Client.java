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

            boolean chat = true;
            while(chat){
                System.out.print("enter message: ");
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
