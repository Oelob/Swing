package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements Observer{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;
//    String ip;
//    int port;
    private String login;
//    int password;

    public static final JTextArea logClient = new JTextArea();
    private final JPanel topPanel = new JPanel(new GridLayout(2,3));
    private final JTextField tflogin = new JTextField();
    private final JTextField tfIPadress = new JTextField();
    private final JPasswordField tfPassword = new JPasswordField();
    private final JTextField tfPort = new JTextField();
    private final JTextField tfmessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final JButton btnLogin = new JButton("Login");
    private final JPanel panelMessage = new JPanel(new BorderLayout());
    public static boolean isLogged;

    // коструктор с возможностью внесения параметров при создании
    ClientGUI(String ip, String port, String login, String password, Observable o){
        this.login = login;
        o.registerClient(this);

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        add(logClient);
        logClient.setEditable(false);
        JScrollPane scrollog = new JScrollPane(logClient);
        add(scrollog);

        // Подключение к серверу
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ServerWindow.log.append("User " + login + " has logged\n");// org/example/Вопросы:2
                if (ServerWindow.isServerWorking == true) {
                    isLogged = true;
                    Controller.userLog(login);
                } else {
                    Controller.errorMsg();
                }
            }
        });

        // отправка сообщений нажатием Enter
        tfmessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ServerWindow.isServerWorking == true) {
                    Controller.sendTxt(tfmessage.getText(), login);

                } else {
                    Controller.errorMsg();
                }
            }
        });

        // отправка сообщений кнопкой Send
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ServerWindow.isServerWorking == true) {
                    Controller.sendTxt(tfmessage.getText(), login);
                } else {
                    Controller.errorMsg();
                }
            }
        });

        setVisible(true);

    }


//    @Override
//    public void update(String message, Observer o) {
//        o.logClient.append(message);
//    }

    @Override
    public void update(String message, ClientGUI c) {

        this.logClient.append(message);
    }
}
