package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 300;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private final JPanel btnPanel = new JPanel();
    private boolean isServerWorking;
    private String msgStart = "Server started\n";
    private String msgStop = "Server stoped\n";

    public ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                //System.out.println("Server stoped " + isServerWorking + "\n");
                log.append(msgStop);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                //System.out.println("Server started " + isServerWorking + "\n");
                log.append(msgStart);
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setTitle("Chat server");
        setAlwaysOnTop(true);
//        setLayout(new GridLayout(1,2));
//        add(btnStart);
//        add(btnStop);

        // добавление панели с кнопками внизу окна
        btnPanel.add(btnStart);
        btnPanel.add(btnStop);
        add(btnPanel, BorderLayout.SOUTH);

        // добавление панели с текстом
        add(log);
        log.setEditable(false);//запрет изменений зоны текста


        setVisible(true);
    }


}
