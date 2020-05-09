package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {

    public JPanel getHerosMenue() {
        return herosMenue;
    }

    private JPanel herosMenue;
    private JLabel herosMenueLabel = new JLabel("First Player:");

    public JLabel getHerosMenueLabel() {
        return herosMenueLabel;
    }

    public GameView(){
//        Full Screen Mood
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        setLayout(new FlowLayout());

//      Heros menue
        herosMenue = new JPanel();
        herosMenue.setLayout(new GridLayout(0,1));
        herosMenue.add(herosMenueLabel);
        add(herosMenue);
        herosMenue.setPreferredSize(new Dimension(400,getHeight()-100));


        //        Exit Button
        JButton exit = new JButton("Exit");

        add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        exit.setSize(new Dimension(50,200));
        exit.setLocation(100,200);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {

    }

}
