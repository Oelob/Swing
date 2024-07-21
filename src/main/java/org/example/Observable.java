package org.example;

public interface Observable {

    void registerClient(ClientGUI o);
    void removeClient(ClientGUI o);

}
