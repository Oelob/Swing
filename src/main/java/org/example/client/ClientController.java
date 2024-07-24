package org.example.client;

import org.example.server.ServerController;

public class ClientController {

    private boolean connected;
    private String name;
    private ClientView clientView;
    private ServerController serverController;

    ClientController(ClientView clientView, ServerController serverController) {
        this.serverController = serverController;
        this.clientView = clientView;
    }


        /**
         * метод подключения к серверу
         */

    public boolean connectToServer (String name) {
        this.name = name;
        if (serverController.connectUser(this)) {
            showOnWindow("Вы успешно подключились!\n");
            connected = true;
            String log = serverController.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    /**
     * Метод отключения от сервера инициализированное сервером
     */
    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    /**
     * Метод отключения от сервера инициализированное клиентом (например закрыто GUI)
     */
    public void disconnectFromServer() {
        serverController.disconnectUser((ClientView) this);
    }

    /**
     * Метод, с помощью которого сервер передает клиенту сообщения
     * @param text текст переданный от сервера
     */
    public void answerFromServer(String text) {
        showOnWindow(text);
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
