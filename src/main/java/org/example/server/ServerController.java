package org.example.server;

import org.example.client.ClientController;
import org.example.client.ClientView;

public class ServerController {

    private ClientController clientController;
    private ServerView serverView;
    private boolean isWork;

    ServerController(ClientController clientController, ServerView serverView){
        this.clientController = clientController;
        this.serverView = serverView;
    }


    public boolean connectUser(ClientController clientController){
        return false;
    };

    public String getHistory(){
        return null;
    };

    public void disconnectUser(ClientView clientView){};

    public void message(String text){
        if (!isWork){
            return;
        }
        serverView.appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
