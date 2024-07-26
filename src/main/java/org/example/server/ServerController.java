package org.example.server;

import org.example.client.ClientView;

import java.util.LinkedList;
import java.util.List;

public class ServerController {
    private final ServerView serverView;
    private boolean isWorking;
    private final  List<ClientView> clients;
    private Repository repository;


    ServerController(ServerView serverView){
        this.serverView = serverView;
        this.clients = new LinkedList<>();
        initRepository();
    }
    private void initRepository (){
        repository = new RepositoryFile();
    }

    public void startServer (){
        if (!isWorking){
            isWorking = true;
            showMessageLogServer("Server turned on\n");
        } else {
            showMessageLogServer("Server already working\n");
        }
    }


    public void stopServer(){
        if (isWorking){
            isWorking = false;
            showMessageLogServer("Server turned off\n");
        } else {
            showMessageLogServer("Server already powered off\n");
        }
    }

    public boolean connectUser(ClientView client) {
        if (isWorking) {
            clients.add(client);
            showMessageLogServer("Clent " + client.infoClient() + " successfully connected");
            return true;
        } else {
            return false;
        }
    }

    public StringBuilder getHistory() {
        return repository.read();
    }

    public void disconnectUser(ClientView clientView) {
        showMessageLogServer("Client " + clientView.infoClient() + " has logged out");
    }

    public void message(String text){
        if (!isWorking){
            return;
        }
        serverView.appendLog(text);
        answerAll(text);
        repository.save(text);
    }

    private void answerAll(String text){
            for (ClientView c : clients) {
                c.showMessage(text);
            }
    }

    private void showMessageLogServer(String text){
        serverView.appendLog(text);
    }

}
