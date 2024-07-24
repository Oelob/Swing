package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ClientView{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;
    private String ip;
    private String  port;
    private String login;
    private String password;

    private final JTextArea log = new JTextArea();
    private final JPanel topPanel = new JPanel(new GridLayout(2,3));
    private final JTextField tflogin = new JTextField();
    private final JTextField tfIPadress = new JTextField();
    private final JPasswordField tfPassword = new JPasswordField();
    private final JTextField tfPort = new JTextField();
    private final JTextField tfmessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final JButton btnLogin = new JButton("Login");
    private final JPanel panelMessage = new JPanel(new BorderLayout());
    private ClientController clientController;

    public ClientGUI(String ip, String port, String login, String password,
                     ClientController clientController){

      this. ip = ip;
      this.port = port;
      this.login = login;
      this.password = password;
      this.clientController = clientController;

//      setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Chat client");

        // добавление верхней панели с текстовыми полями
        tfIPadress.setText(ip);
        tfPort.setText(port);
        tflogin.setText(login);
        tfPassword.setText(password);

        topPanel.add(tfIPadress);
        topPanel.add(tfPort);
        topPanel.add(tflogin);
        topPanel.add(tfPassword);
        topPanel.add(btnLogin);
        add(topPanel, BorderLayout.NORTH);

        // добавление нижней панели с текстовым полем и кнопкой отправки сообщения
        tfmessage.setColumns(350);
        panelMessage.add(tfmessage, BorderLayout.CENTER);
        panelMessage.add(btnSend, BorderLayout.EAST);
        add(panelMessage, BorderLayout.SOUTH);

        // добавление текстовой зоны
        add(log);
        log.setEditable(false);
        JScrollPane scrollog = new JScrollPane(log);
        add(scrollog);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.connectToServer(login);
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.message(tfmessage.getText());
            }
        });

        setVisible(true);

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
        log.append(message);
    }

    @Override
    public void disconnectedFromServer() {
        hideHeaderPanel(true);
    }

    /**
     * Метод, описывающий отключение клиента от сервера со стороны клиента
     */
    public void disconnectFromServer(){
        clientController.disconnectFromServer();
    }
}
