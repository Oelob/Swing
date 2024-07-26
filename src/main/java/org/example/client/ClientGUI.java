package org.example.client;

import org.example.server.ServerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ClientGUI extends JFrame implements ClientView{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;

    private final JTextArea log;
    private final JPanel topPanel;
    private final JTextField tflogin;
    private final JTextField tfmessage;
    private final ClientController clientController;



    public ClientGUI(String ip, String port, String login, String password) {

        clientController = new ClientController(this);


//      setDefaultCloseOperation();
        addWindowListener(new WindowListener() {
          @Override
          public void windowOpened(WindowEvent e) {

          }

          @Override
          public void windowClosing(WindowEvent e) {
              disconnectFromServer();
          }

          @Override
          public void windowClosed(WindowEvent e) {

          }

          @Override
          public void windowIconified(WindowEvent e) {

          }

          @Override
          public void windowDeiconified(WindowEvent e) {

          }

          @Override
          public void windowActivated(WindowEvent e) {

          }

          @Override
          public void windowDeactivated(WindowEvent e) {

          }
      });
        setSize(WIDTH,HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Chat client");

        // добавление верхней панели с текстовыми полями
        JTextField tfIPadress = new JTextField();
        tfIPadress.setText(ip);
        JTextField tfPort = new JTextField();
        tfPort.setText(port);
        tflogin = new JTextField();
        tflogin.setText(login);
        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setText(password);

        topPanel = new JPanel(new GridLayout(2,3));
        topPanel.add(tfIPadress);
        topPanel.add(tfPort);
        topPanel.add(tflogin);
        topPanel.add(tfPassword);
        JButton btnLogin = new JButton("Login");
        topPanel.add(btnLogin);
        add(topPanel, BorderLayout.NORTH);

        // добавление нижней панели с текстовым полем и кнопкой отправки сообщения
        tfmessage = new JTextField();
        tfmessage.setColumns(350);
        JPanel panelMessage = new JPanel(new BorderLayout());
        panelMessage.add(tfmessage, BorderLayout.CENTER);
        JButton btnSend = new JButton("Send");
        panelMessage.add(btnSend, BorderLayout.EAST);
        add(panelMessage, BorderLayout.SOUTH);

        // добавление текстовой зоны
        log = new JTextArea();
        add(log);
        log.setEditable(false);
        JScrollPane scrollog = new JScrollPane(log);
        add(scrollog);


        btnLogin.addActionListener(e -> clientController.connectToServer(login));

        btnSend.addActionListener(e -> clientController.message(tfmessage.getText()));

        tfmessage.addActionListener(e -> clientController.message(tfmessage.getText()));

        setVisible(true);

    }
    public void registerOnServer(ServerController serverController){
        clientController.setServer(serverController);
    }



    /**
     * Метод изменения видимости верхней панели экрана, на которой виджеты для авторизации (например кнопка логин)
     * @param visible true, если надо сделать панель видимой
     */
    public void hideHeaderPanel(boolean visible){
        topPanel.setVisible(visible);
    }



    @Override
    public void showMessage(String message) {
        log.append(message + "\n");
    }

    @Override
    public void disconnectedFromServer() {
        hideHeaderPanel(true);
    }

    @Override
    public String infoClient() {
        return tflogin.getText();
    }

    /**
     * Метод, описывающий отключение клиента от сервера со стороны клиента
     */
    public void disconnectFromServer(){
        clientController.disconnectFromServer();
    }
}
