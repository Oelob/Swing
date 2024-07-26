package org.example.client;

import org.example.server.ServerController;

public class ClientController {

    private boolean connected;
    private String name;
    private final ClientView clientView;
    private ServerController serverController;

    ClientController(ClientView clientView) {

        this.clientView = clientView;
    }

    public void setServer(ServerController serverController) {
        this.serverController = serverController;
    }


    /**
     * метод подключения к серверу
     */

    public void connectToServer (String name) {
        this.name = name;

        if (serverController.connectUser(this.clientView)) {
            showOnWindow("Вы успешно подключились!\n");
            connected = true;
            StringBuilder log = serverController.getHistory();
            if (log != null) {
                showOnWindow(String.valueOf(log));
            }
        } else {
            showOnWindow("Подключение не удалось");
        }
    }


    /**
     * Метод отключения от сервера инициализированное клиентом (например закрыто GUI)
     */
    public void disconnectFromServer() {
        serverController.disconnectUser(clientView);
        connected = false;
    }

    /**
     * Метод для передачи сообщения на сервер
     * @param text текст передаваемого сообщения
     */
    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                serverController.message(name + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }


    /**
     * Метод вывода сообщения на GUI
     * @param text текст, который требуется вывести на экран
     */

    private void showOnWindow (String text){
        clientView.showMessage(text);
    }

}
