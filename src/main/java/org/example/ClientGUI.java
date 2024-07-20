package org.example;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;
//    String ip;
//    int port;
//    String login;
//    int password;

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

    ClientGUI(String ip, String port, String login, String password){

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
        add(log);
        log.setEditable(false);
        JScrollPane scrollog = new JScrollPane(log);
        add(scrollog);

        setVisible(true);

    }


}
