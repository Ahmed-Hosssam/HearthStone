package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {

    private JPanel herosMenue;

    GameView (){
//        Full Screen Mood
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
//        Exit Button
        JButton exit = new JButton("Exit");
        add(exit, BorderLayout.SOUTH);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
//      Heros menue at the beginning


        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new GameView();
    }

}
