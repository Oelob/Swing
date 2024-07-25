package org.example.server;

import org.example.client.ClientController;
import org.example.client.ClientGUI;
import org.example.client.ClientView;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private ClientController clientController;
    private final ServerView serverView;
    private boolean isWork;
    private final  List<ClientView> clients;

    ServerController(ServerView serverView){
        this.serverView = serverView;
        this.clients = new ArrayList<>();
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public ServerController getServerController(){
        return this;
    }


    public void startServer (){
        if (isWork == false){
            isWork = true;
            showMessageLogServer("Server turned on\n");
        } else {
            showMessageLogServer("Server already working\n");
        }
    }


    public void stopServer(){
        if (isWork == true){
            isWork = false;
            showMessageLogServer("Server turned off\n");
        } else {
            showMessageLogServer("Server already powered off\n");
        }
    }

    public boolean connectUser(ClientView client) {

        clients.add(client);
        for (ClientView c : clients){
            System.out.println(c.infoClient());
        }
        return true;
    }

    ;

    public String getHistory() {
        return null;
    }

    ;

    public void disconnectUser(ClientView clientView) {
    }

    ;


    public void message(String text){
        if (!isWork){
            return;
        }
        serverView.appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text){
//        for (ClientGUI clientGUI: clientGUIList){
//            clientGUI.answer(text);
//        }
    }

    private void saveInLog(String text){
//        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
//            writer.write(text);
//            writer.write("\n");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    private void showMessageLogServer(String text){
        serverView.appendLog(text);
    }
}
