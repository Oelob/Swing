package org.example.client;

public interface ClientView {

    void showMessage(String message);
    void disconnectedFromServer();
    String infoClient();

}
