package org.example;


import org.example.client.ClientGUI;
import org.example.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        ClientGUI client1 =  new ClientGUI("123.12.0.4", "3333", "Ivan_Ivanov", "2323322");
        ClientGUI client2 = new ClientGUI("123.45.0.5", "8796", "Igor_Petrov", "876543");
        client1.registerOnServer(serverWindow.getServerController());
        client2.registerOnServer(serverWindow.getServerController());

    }
}