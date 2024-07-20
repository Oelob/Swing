package org.example;

public class Controller {

    public static void sendTxt (String text){
        if (ClientGUI.isLogged == true) {
            ServerWindow.log.append(text + "\n");// проверка на подключение к серверу
        } else {
            ClientGUI.logClient.append("You need to login!\n");
        }
    }

    public static void userLog (String login){
        ServerWindow.log.append("User " + login + " has logged\n");
    }

    public static void errorMsg (){
        ClientGUI.logClient.append("Server doesn't working\n");
    }
}
