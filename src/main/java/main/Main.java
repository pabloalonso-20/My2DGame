package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();//causes this window to be sized to fit the referred
                      //size and layouts its subcomponents (gamepanel)

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //testing it runs
        gamePanel.startGameThread();


    }
}
