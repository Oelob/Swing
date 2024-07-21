package org.example;

public class Controller {

    public static void sendTxt (String text, String login){
        if (ClientGUI.isLogged == true) {// проверка на подключение к серверу
            ServerWindow.log.append(login + ": " + text + "\n");
            ClientGUI.logClient.append(login + ": " + text + "\n");
        } else {
            ClientGUI.logClient.append("You need to login!\n");
        }
    }

    // Логирование пользователя
    public static void userLog (String login){
        ServerWindow.log.append("User " + login + " has logged\n");
    }

    public static void errorMsg (){
        ClientGUI.logClient.append("Server doesn't working\n");
    }

    public static void writeFile(String text){

    }
}
