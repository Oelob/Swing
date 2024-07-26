package org.example.server;

import javax.swing.*;
import java.awt.BorderLayout;

public class ServerWindow extends JFrame implements ServerView {
    private static final int POS_X = 500;
    private static final int POS_Y = 300;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private final JPanel btnPanel = new JPanel();

    private final ServerController serverController;

    public ServerWindow(){
        this.serverController = new ServerController(this);
        initWindow();
    }

    public ServerController getServerController() {
        return serverController;
    }

    private void initWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        // добавление панели с кнопками внизу окна
        btnPanel.add(btnStart);
        btnPanel.add(btnStop);
        add(btnPanel, BorderLayout.SOUTH);

        btnStop.addActionListener(e -> serverController.stopServer());

        btnStart.addActionListener(e -> serverController.startServer());

        // добавление панели с текстом
        add(log);
        log.setEditable(false);//запрет изменений зоны текста
        JScrollPane scrollog = new JScrollPane(log);
        add(scrollog);

        setVisible(true);
    }


    @Override
    public void appendLog(String text) {
        log.append(text + "\n");
    }
}
