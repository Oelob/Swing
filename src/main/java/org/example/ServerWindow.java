package org.example;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame implements Observable {
    private static final int POS_X = 500;
    private static final int POS_Y = 300;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    public static final JTextArea log = new JTextArea();
    private final JPanel btnPanel = new JPanel();
    public static boolean isServerWorking;
    private String msgStart = "Server started\n";
    private String msgStop = "Server stoped\n";
    private String msgServerWorking = "Server already working\n";
    private String msgServerNotWorking = "Server isn't running\n";
    public static String msgFromClient;
    private static List<ClientGUI> clients;

    public ServerWindow(){
        clients = new ArrayList<>();

        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking == false) {
                    log.append(msgServerNotWorking);
                } else {
                    isServerWorking = false;
                    //System.out.println("Server stoped " + isServerWorking + "\n");
                    log.append(msgStop);
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking == true){
                    log.append(msgServerWorking);
                } else {
                isServerWorking = true;
                //System.out.println("Server started " + isServerWorking + "\n");
                log.append(msgStart);
                }
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

    public static void sendMessage (){

        for (ClientGUI o : clients) {
//            o.update(msgFromClient);

            o.logClient.append(msgFromClient);
            System.out.println(o.getLogin());
        }
    }

    @Override
    public void registerClient(ClientGUI o) {
        clients.add(o);
    }

    @Override
    public void removeClient(ClientGUI o) {
        clients.remove(o);
    }

}
