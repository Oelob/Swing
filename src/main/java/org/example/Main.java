package org.example;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI("123.12.0.4", "3333", "Ivan_Ivanov", "2323322", serverWindow);
        new ClientGUI("123.45.0.5", "8796", "Igor_Petrov", "876543", serverWindow);
//        new ClientGUI("134.33.3.4", "2323", "Sergei", "4532134");
//        new ClientGUI(serverWindow);
//        new ClientGUI(serverWindow);






    }
}